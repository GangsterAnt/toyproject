package com.example.board.service.post;

import com.example.board.domain.BoardPageSizeEnum;
import com.example.board.domain.BoardPageSortEnum;
import com.example.board.service.comment.Comment;
import com.example.board.domain.PageableWrapper;
import com.example.board.service.comment.CommentQueryService;
import com.example.board.service.post.post.PostEntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
 class PostQueryService {

    private final PostRepository postRepository;
    private final PostEntityConverter postEntityConverter;

    private final CommentQueryService commentQueryService;

    public Post getPostById(Long id) {
        Optional<PostEntity> postOptional = postRepository.findActivePostByRootPostId(id);
        if (postOptional.isEmpty()) {
            return null; //TODO return 404
        }

        PostEntity postEntity = postOptional.get();

        //get root comments
        PageableWrapper commentPageableWrapper = new PageableWrapper(0, BoardPageSizeEnum.COMMENT_DEFAULT_SIZE, BoardPageSortEnum.CREATED_DATE_ASC);
        List<Comment> collect = commentQueryService.getRootCommentListByPostId(id, commentPageableWrapper);

        //get child comments
        for (Comment comment : collect) {
            List<Comment> childComments = commentQueryService.getChildCommentsListByParentCommentId(comment.getCommentId(), commentPageableWrapper.getPageable());
            comment.setChildCommentList(childComments);
        }

        return postEntityConverter.convertFromEntityWithComments(postEntity, collect);
    }

    public List<PostSummary> getAllPosts(boolean filterDeleted, PageableWrapper pageable) {
        List<PostEntity> allPostEntities = filterDeleted ? postRepository.findActivePostByPage(pageable.getPageable()) : postRepository.findAllPostByPage(pageable.getPageable());

        return allPostEntities.stream()
                .map(postEntityConverter::convertToPostSummary)
                .collect(Collectors.toList());
    }

    public Long createPost(Post post) {
        PostEntity postEntity = postEntityConverter.convertFromDomainModel(post);
        PostEntity response = postRepository.saveAndFlush(postEntity);//Do not update only insert
        return response.getPostId();
    }

    public boolean softDeletePost(Long id) {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        Instant instant = now.toInstant();
        Timestamp deleteAt = Timestamp.from(instant);
        return postRepository.softDeleteById(id, deleteAt);
    }

    public boolean hardDeletePost(Long id) {
        try {
            postRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            log.warn("Failed to delete post: {}", id, e);
            return false;
        }
    }

    public boolean hidePost(Long id) {
        return postRepository.hidePostById(id);
    }

    public boolean unHidePost(Long id) {
        return postRepository.unHidePostById(id);
    }
}
