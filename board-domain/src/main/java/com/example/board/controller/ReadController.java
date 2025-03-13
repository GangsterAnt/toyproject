package com.example.board.controller;

import com.example.board.dto.PostDto;
import com.example.board.dto.PostListDto;
import com.example.board.model.PostBo;
import com.example.board.service.ReadService;
import com.example.board.service.converter.PostDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReadController {

    private final ReadService readService;
    private final PostDtoConverter postDtoConverter;

    @GetMapping
    public PostDto getPostById(@RequestParam Long id) {
        PostBo postById = readService.getPostById(id);
        return postDtoConverter.convertToDto(postById);
    }

    @GetMapping
    public PostListDto getPostList() {
        return readService.getPostList();
    }
}
