package com.example.board.controller;

import com.example.board.service.comment.CommentService;
import com.example.board.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HideController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/hide/post")
    public String hidePost(@RequestParam Long id) {
        if (postService.hidePost(id)) {
            return "admin/hide-success";
        }
        return "admin/hide-fail";
    }

    @PostMapping("/un-hide/post")
    public String unHidePost(@RequestParam Long id) {
        if (postService.unHidePost(id)) {
            return "admin/un-hide-success";
        }
        return "admin/un-hide-fail";
    }

    @PostMapping("/hide/comment")
    public String hideComment(@RequestParam Long id) {
        if (commentService.hideComment(id)) {
            return "admin/hide-success";
        }
        return "admin/hide-fail";
    }

    @PostMapping("/un-hide/comment")
    public String unHideComment(@RequestParam Long id) {
        if (commentService.unHideComment(id)) {
            return "admin/un-hide-success";
        }
        return "admin/un-hide-fail";
    }
}
