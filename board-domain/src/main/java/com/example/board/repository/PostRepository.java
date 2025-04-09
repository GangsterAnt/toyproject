package com.example.board.repository;

import com.example.board.model.PostEntity;
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
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    Optional<PostEntity> findByPostId(@Param("post_id") Long playerId);

    @Query("SELECT p FROM Post p WHERE p.postId = :postId AND (p.deletedAt IS NULL AND p.hidden = false)")
    Optional<PostEntity> findActivePostByRootPostId(@Param("postId") Long postId);

    @Query("SELECT p FROM Post p WHERE (p.deletedAt IS NULL AND p.hidden = false)")
    List<PostEntity> findActivePostByPage(Pageable pageable);

    @Query("SELECT p FROM Post p WHERE (p.deletedAt IS NULL)")
    List<PostEntity> findAllPostByPage(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.hidden = true WHERE p.id = :id")
    boolean hidePostById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.hidden = false WHERE p.id = :id")
    boolean unHidePostById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.deletedAt = :deletedAt WHERE p.id = :id")
    boolean softDeleteById(@Param("id") Long id, @Param("deletedAt") Timestamp deletedAt);

    List<PostEntity> findAll();
}
