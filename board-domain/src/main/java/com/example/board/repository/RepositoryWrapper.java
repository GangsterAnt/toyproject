package com.example.board.repository;

import com.example.board.domain.BoardPageSizeEnum;
import com.example.board.domain.BoardPageSortEnum;
import com.example.board.domain.Comment;
import com.example.board.domain.PageableWrapper;
import com.example.board.domain.Post;
import com.example.board.domain.PostSummary;
import com.example.board.model.PostEntity;
import com.example.board.service.converter.comment.CommentEntityConverter;
import com.example.board.service.converter.post.PostEntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class RepositoryWrapper {    //TODO split this by its role

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    private final CommentEntityConverter commentEntityConverter;
    private final PostEntityConverter postEntityConverter;

    public Post getPostById(Long id) {
        Optional<PostEntity> postOptional = postRepository.findActivePostByRootPostId(id);
        if (postOptional.isEmpty()) {
            return null; //TODO return 404
        }

        PostEntity postEntity = postOptional.get();

        //get root comments
        PageableWrapper commentPageableWrapper = new PageableWrapper(0, BoardPageSizeEnum.COMMENT_DEFAULT_SIZE, BoardPageSortEnum.CREATED_DATE_ASC);
        List<Comment> collect = getRootCommentListByPostId(id, commentPageableWrapper);

        //get child comments
        for (Comment comment : collect) {
            List<Comment> childComments = getChildCommentsListByParentCommentId(comment.getCommentId(), commentPageableWrapper.getPageable());
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

    public List<Comment> getRootCommentListByPostId(Long id, PageableWrapper pageableWrapper) {
        return Optional.ofNullable(commentRepository.findActiveRootCommentsByRootPostId(id, pageableWrapper.getPageable()))
                .stream()
                .flatMap(List::stream)
                .map(commentEntityConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public List<Comment> getChildCommentsListByParentCommentId(Long id, Pageable pageable) {
        return Optional.ofNullable(commentRepository.findActiveChildCommentsByParentCommentId(id, pageable))
                .stream()
                .flatMap(List::stream)
                .map(commentEntityConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public List<Comment> getChildComments(Long commentId, PageableWrapper pageableWrapper) {
        return Optional.ofNullable(commentRepository.findActiveChildCommentsByParentCommentId(commentId, pageableWrapper.getPageable()))
                .stream()
                .flatMap(List::stream)
                .map(commentEntityConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public boolean hidePost(Long id) {
        try {
            postRepository.hidePostById(id);
            return true;
        } catch (Exception e) {
            log.warn("Failed to hide post: {}", id, e);
            return false;
        }
    }

    public boolean unHidePost(Long id) {
        try {
            postRepository.unHidePostById(id);
            return true;
        } catch (Exception e) {
            log.warn("Failed to un-hide post: {}", id, e);
            return false;
        }
    }

    public boolean hideComment(Long id) {
        try {
            postRepository.hideCommentById(id);
            return true;
        } catch (Exception e) {
            log.warn("Failed to hide post: {}", id, e);
            return false;
        }
    }

    public boolean unHideComment(Long id) {
        try {
            postRepository.unHideCommentById(id);
            return true;
        } catch (Exception e) { // 나중에 밑에서 throw 해버리지 말기,
            log.warn("Failed to un-hide post: {}", id, e);
            return false;
        }
    }

    public boolean hardDeletePost(Long id) {
        try {
            postRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.warn("Failed to delete post: {}", id, e);
            return false;
        }
    }

    public boolean softDeletePost(Long id) {
        try {
            ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
            Instant instant = now.toInstant();
            Timestamp deleteAt = Timestamp.from(instant);
            postRepository.softDeleteById(id, deleteAt);
            return true;
        } catch (Exception e) {
            log.error("Failed to delete post: {}", id, e);
            return false;
        }
    }

    public Long createPost(Post post) {
        PostEntity postEntity = postEntityConverter.convertFromDomainModel(post);
        PostEntity response = postRepository.saveAndFlush(postEntity);//Do not update only insert
        return response.getPostId();
    }

//    public List<PostSummaryBo> getPostSummaryList() {
//        List<Post> allPost = postRepository.findAll();
//        return postSummaryBoConverter.convertToBoList(allPost);
//    }
}
