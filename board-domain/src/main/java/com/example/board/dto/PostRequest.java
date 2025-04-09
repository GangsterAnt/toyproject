package com.example.board.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostRequest {
    private String title;
    private String content;
    private String ownerMemberId;

    public boolean isValidRequest() {
        return title != null && !title.isEmpty() &&
               content != null && !content.isEmpty() &&
               ownerMemberId != null && !ownerMemberId.isEmpty();
    }

    public boolean isInvalidRequest() {
        return !isValidRequest();
    }
}
