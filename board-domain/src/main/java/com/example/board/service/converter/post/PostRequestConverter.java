package com.example.board.service.converter.post;

import com.example.board.domain.Post;
import com.example.board.dto.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostRequestConverter {

    public Post convertPostFromPostRequest(PostRequest postRequest) {
        return Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .ownerMemberId(postRequest.getOwnerMemberId())
                .build();
    }
}
