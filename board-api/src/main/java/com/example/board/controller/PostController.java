package com.example.board.controller;

import com.example.board.domain.PostSummary;
import com.example.board.dto.PostDto;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts") //create 라는 단어는 사용하지 않음, Restful하게 짜는 방법. POST api임으로 post를 만드는것을 의미함
    public ResponseEntity<String> createPost(@RequestBody PostDto newPost) {
        Long newPostId = postService.createPost(newPost);
        if (newPostId != null) {
            return ResponseEntity.ok("Post created successfully newPostId: " + newPostId);
        } else {
            return ResponseEntity.status(500).body("Failed to create post. Please check error logs.");
        }
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id) {
        PostDto postById = postService.getPostById(id);
        if (postById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(postById);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostSummary>> getAllPost(@RequestParam Long pageNumber) {
        List<PostSummary> postSummaryList = postService.getAllPost(true, pageNumber);
        if (postSummaryList == null || postSummaryList.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(postSummaryList);
    }
}
