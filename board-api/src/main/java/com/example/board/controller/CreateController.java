package com.example.board.controller;

import com.example.board.dto.PostDto;
import com.example.board.service.CreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateController {

    private final CreateService createService;

    @PostMapping("/create/post")
    public ResponseEntity<String> createPost(@RequestBody PostDto newPost) {
        Long newPostId = createService.createPost(newPost);
        if (newPostId != null) {
            return ResponseEntity.ok("Post created successfully newPostId: " + newPostId);
        } else {
            return ResponseEntity.status(500).body("Failed to create post. Please check error logs.");
        }
    }
}
