package com.example.board.controller;

import com.example.board.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DeleteController {

    private final PostService postService;

    @DeleteMapping("/delete/soft/post")
    public String softDeletePost(@RequestParam Long id) {
        boolean b = postService.softDeletePost(id);
        return "soft-delete-post";
    }

    @DeleteMapping("/delete/hard/post")
    public String hardDeletePost(@RequestParam Long id) {
        return "hard-delete-post";
    }
}
