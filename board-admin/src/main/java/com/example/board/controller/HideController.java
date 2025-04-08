package com.example.board.controller;

import com.example.board.service.HideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HideController {

    private final HideService hideService;

    @PostMapping("/hide/post")
    public String hidePost(@RequestParam Long id) {
        if (hideService.hidePost(id)) {
            return "admin/hide-success";
        }
        return "admin/hide-fail";
    }

    @PostMapping("/un-hide/post")
    public String unHidePost(@RequestParam Long id) {
        if (hideService.unHidePost(id)) {
            return "admin/un-hide-success";
        }
        return "admin/un-hide-fail";
    }

    @PostMapping("/hide/comment")
    public String hideComment(@RequestParam Long id) {
        if (hideService.hideComment(id)) {
            return "admin/hide-success";
        }
        return "admin/hide-fail";
    }

    @PostMapping("/un-hide/comment")
    public String unHideComment(@RequestParam Long id) {
        if (hideService.unHideComment(id)) {
            return "admin/un-hide-success";
        }
        return "admin/un-hide-fail";
    }
}
