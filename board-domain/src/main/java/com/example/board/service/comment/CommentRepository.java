package com.example.board.service.comment;

import com.example.board.service.comment.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByRootPostId(@Param("rootPostId") Long rootPostId);

    @Query("SELECT c FROM Comment c WHERE c.rootPostId = :rootPostId AND (c.deletedAt IS NULL AND c.hidden = false)")
    List<CommentEntity> findActiveCommentsByRootPostId(@Param("rootPostId") Long rootPostId);

    @Query("SELECT c FROM Comment c WHERE c.rootPostId = :rootPostId AND (c.deletedAt IS NULL AND c.hidden = false AND c.parentCommentId IS NULL)")
    List<CommentEntity> findActiveRootCommentsByRootPostId(@Param("rootPostId") Long rootPostId, Pageable pageable);

    @Query("SELECT c FROM Comment c WHERE c.parentCommentId = :parentCommentId AND (c.deletedAt IS NULL AND c.hidden = false)")
    List<CommentEntity> findActiveChildCommentsByParentCommentId(@Param("parentCommentId") Long parentCommentId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Comment c SET c.hidden = true WHERE c.id = :id")
    boolean hideCommentById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Comment c SET c.hidden = false WHERE c.id = :id")
    boolean unHideCommentById(Long id);
}
