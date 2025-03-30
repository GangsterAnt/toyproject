package com.example.board.controller;

import com.example.board.bo.PostSummaryBo;
import com.example.board.service.ReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class adminController {

    private ReadService readService;

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("title", "Admin Dashboard");
        return "admin/index"; // templates/admin/index.html을 반환
    }

    @GetMapping("/admin/allpost")
    public String adminPageGetAllPost(Model model) {
        List<PostSummaryBo> allPost = readService.getAllPost(true);
        return "admin/index"; // templates/admin/index.html을 반환
    }
}
