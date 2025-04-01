package com.example.board.repository;

import com.example.board.model.Comment;
import com.example.board.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByPostId(@Param("post_id") Long playerId);

    @Query("SELECT p FROM Post p WHERE p.postId = :postId AND (p.deletedAt IS NULL AND p.hidden = false)")
    Optional<Post> findActivePostByRootPostId(@Param("postId") Long postId);

    @Query("SELECT p FROM Post p AND (p.deletedAt IS NULL AND p.hidden = false)")
    List<Post> findActivePostByPage(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.hidden = true WHERE p.id = :id")
    void hidePostById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.hidden = false WHERE p.id = :id")
    void unHidePostById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Comment c SET c.hidden = true WHERE c.id = :id")
    void hideCommentById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Comment c SET c.hidden = false WHERE c.id = :id")
    void unHideCommentById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.deletedAt = :deletedAt WHERE p.id = :id")
    void softDeleteById(@Param("id") Long id, @Param("deletedAt") Timestamp deletedAt);

    List<Post> findAll();
}
