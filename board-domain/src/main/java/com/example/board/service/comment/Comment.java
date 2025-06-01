package com.example.board.service.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@Getter
public class Comment {
    private Long commentId;
    private String content;
    private String ownerMemberId;
    private Long rootPostId;

    private Integer likes;
    private Integer dislikes;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Nullable
    private Long parentCommentId;

    @Nullable
    @Setter
    private List<Comment> childCommentList;

    public Integer getChildCommentCount() {
        return childCommentList.size();
    }

    public CommentDto convertToDto() {
        return null;
    }
}

/*
update history 23 3 2025
대댓글 계층구조를
댓글 -> 대댓글 ->대대댓글 에서
댓글 -> 대댓글 리스트로 변환. createdAt으로 정렬
 */
