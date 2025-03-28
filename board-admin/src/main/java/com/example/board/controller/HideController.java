package com.example.board.controller;

import com.example.board.service.HideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HideController {

    private final HideService hideService;

    @PostMapping("/hide/post")
    public String hidePost(@RequestParam Long id) {
        if (hideService.hidePost(id)) {
            return "hide post complete. post id : " + id;
        }
        return "hide fail. Please check warning log.";
    }

    @PostMapping("/un-hide/post")
    public String unHidePost(@RequestParam Long id) {
        if (hideService.unHidePost(id)) {
            return "un-hide post complete. post id : " + id;
        }
        return "un-hide fail. Please check warning log.";
    }

    @PostMapping("/hide/comment")
    public String hideComment(@RequestParam Long id) {
        if (hideService.hideComment(id)) {
            return "hide comment complete. post id : " + id;
        }
        return "hide fail. Please check warning log.";
    }

    @PostMapping("/un-hide/comment")
    public String unHideComment(@RequestParam Long id) {
        if (hideService.unHideComment(id)) {
            return "un-hide comment complete. post id : " + id;
        }
        return "un-hide fail. Please check warning log.";
    }
}
