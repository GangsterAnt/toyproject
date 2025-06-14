package com.example.board.service.post.post;

import com.example.board.service.post.Post;
import com.example.board.service.post.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Component
public class PostRequestConverter {

    public Post convertPostFromPostRequest(PostRequest postRequest) {
        return Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .ownerMemberId(postRequest.getOwnerMemberId())
                .createdAt(ZonedDateTime.now(ZoneId.of("UTC")).toLocalDateTime())
                .updatedAt(ZonedDateTime.now(ZoneId.of("UTC")).toLocalDateTime())
                .deletedAt(null)
                .likes(0)
                .dislikes(0)
                .commentList(null)
                .build();
    }
}
