package com.example.board.repository;

import com.example.board.bo.BoardPageSizeEnum;
import com.example.board.bo.BoardPageSortEnum;
import com.example.board.bo.CommentBo;
import com.example.board.bo.PageableWrapper;
import com.example.board.bo.PostBo;
import com.example.board.bo.PostSummaryBo;
import com.example.board.model.Post;
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

    public PostBo getPostById(Long id) {
        Optional<Post> postOptional = postRepository.findActivePostByRootPostId(id);
        if (postOptional.isEmpty()) {
            return null; //TODO return 404
        }

        Post post = postOptional.get();

        //get root comments
        PageableWrapper commentPageableWrapper = new PageableWrapper(0, BoardPageSizeEnum.COMMENT_DEFAULT_SIZE, BoardPageSortEnum.CREATED_DATE_ASC);
        List<CommentBo> collect = getRootCommentListByPostId(id, commentPageableWrapper);

        //get child comments
        for (CommentBo commentBo : collect) {
            List<CommentBo> childComments = getChildCommentsListByParentCommentId(commentBo.getCommentId(), commentPageableWrapper.getPageable());
            commentBo.setChildCommentBoList(childComments);
        }

        return postEntityConverter.convertFromEntityWithComments(post, collect);
    }

    public List<PostSummaryBo> getAllPosts(boolean filterDeleted, PageableWrapper pageable) {
        List<Post> allPosts = filterDeleted ? postRepository.findActivePostByPage(pageable.getPageable()) : postRepository.findAllPostByPage(pageable.getPageable());

        return allPosts.stream()
                .map(postEntityConverter::convertToPostSummaryBo)
                .collect(Collectors.toList());
    }

    public List<CommentBo> getRootCommentListByPostId(Long id, PageableWrapper pageableWrapper) {
        return Optional.ofNullable(commentRepository.findActiveRootCommentsByRootPostId(id, pageableWrapper.getPageable()))
                .stream()
                .flatMap(List::stream)
                .map(commentEntityConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public List<CommentBo> getChildCommentsListByParentCommentId(Long id, Pageable pageable) {
        return Optional.ofNullable(commentRepository.findActiveChildCommentsByParentCommentId(id, pageable))
                .stream()
                .flatMap(List::stream)
                .map(commentEntityConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public List<CommentBo> getChildComments(Long commentId, PageableWrapper pageableWrapper) {
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
        } catch (Exception e) {
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

    public boolean createPost(PostBo postBo) {
        try {
            Post post = postEntityConverter.convertFromBo(postBo);
            postRepository.saveAndFlush(post); //Do not update only insert
            return true;
        } catch (Exception e) {
            log.error("Failed to create post: {}", postBo, e);
            return false;
        }
    }

//    public List<PostSummaryBo> getPostSummaryList() {
//        List<Post> allPost = postRepository.findAll();
//        return postSummaryBoConverter.convertToBoList(allPost);
//    }
}
