package com.example.board.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Builder
@Getter
public class CommentBo {
    private Long commentId;
    private String content;
    private String ownerMemberId;
    private Long rootPostId;

    private Integer likes;
    private Integer dislikes;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Long childCommentId;
    @Setter
    private CommentBo childCommentBo;

    private Long parentCommentId;
    @Setter
    private CommentBo parentCommentBo;
}

/*
1.댓글
    대댓글입니다
        대대댓글입니다.

 2. 댓글
    대댓글입니다.
    대대댓글입니다.
 */
