package com.example.board.controller;

import com.example.board.service.post.PostSummary;
import com.example.board.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class adminController {

    private final PostService postService;

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("title", "Admin Dashboard");
        return "admin/index"; // templates/admin/index.html을 반환
    }

    @GetMapping("/admin/allpost")
    public String adminPageGetAllPost(Model model, Long pageNumber) {
        List<PostSummary> allPost = postService.getAllPost(false, pageNumber);
        model.addAttribute("allPost", allPost);
        return "admin/index"; // templates/admin/index.html을 반환
    }
}
