package com.example.board.repository;

import com.example.board.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByRootPostId(@Param("rootPostId") Long rootPostId);

    @Query("SELECT c FROM Comment c WHERE c.rootPostId = :rootPostId AND (c.deletedAt IS NULL AND c.hidden = false)")
    List<Comment> findActiveCommentsByRootPostId(@Param("rootPostId") Long rootPostId);

    @Query("SELECT c FROM Comment c WHERE c.rootPostId = :rootPostId AND (c.deletedAt IS NULL AND c.hidden = false AND c.parentCommentId IS NULL)")
    List<Comment> findActiveRootCommentsByRootPostId(@Param("rootPostId") Long rootPostId, Pageable pageable);

    @Query("SELECT c FROM Comment c WHERE c.parentCommentId = :parentCommentId AND (c.deletedAt IS NULL AND c.hidden = false)")
    List<Comment> findActiveChildCommentsByParentCommentId(@Param("parentCommentId") Long parentCommentId, Pageable pageable);
}
