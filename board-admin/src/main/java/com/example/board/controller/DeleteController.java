package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteController {

    @DeleteMapping("/delete/soft/post")
    public String softDeletePost(@RequestParam Long id) {
        return "soft-delete-post";
    }

    @DeleteMapping("/delete/hard/post")
    public String hardDeletePost(@RequestParam Long id) {
        return "hard-delete-post";
    }
}
