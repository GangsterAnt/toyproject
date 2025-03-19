package com.example.board.controller;

import com.example.board.dto.CommentDto;
import com.example.board.dto.PostDto;
import com.example.board.service.ReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final ReadService readService;

    @GetMapping("/get/post")
    public PostDto getPost(
            @RequestParam(value = "id", required = false) Long id
    ) {
        return readService.getPostById(id);
    }

    @GetMapping("/get/comments")
    public List<CommentDto> getCommentList(
            @RequestParam(value = "rootPostId", required = false) Long rootPostId
    ) {
        return readService.getCommentByRootPostId(rootPostId);
    }
}
