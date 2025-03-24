package com.example.board.controller;

import com.example.board.dto.PostDto;
import com.example.board.service.ReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReadController {

    private final ReadService readService;

    @GetMapping("/getPostById")
    public PostDto getPostById(@RequestParam Long id) {
        return readService.getPostById(id);
    }

//    @GetMapping("/getPostList")
//    public PostListDto getPostList() {
//        return readService.getPostList();
//    }
}
