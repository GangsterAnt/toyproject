package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminController {

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("title", "Admin Dashboard");
        return "admin/index"; // templates/admin/index.html을 반환
    }
}
