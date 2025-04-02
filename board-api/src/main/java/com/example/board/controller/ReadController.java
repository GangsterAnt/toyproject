package com.example.board.controller;

import com.example.board.bo.PostSummaryBo;
import com.example.board.dto.CommentDto;
import com.example.board.dto.PostDto;
import com.example.board.service.ReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ReadController {

    private final ReadService readService;

    @GetMapping("/getPostById")
    public PostDto getPostById(@RequestParam Long id) {
        return readService.getPostById(id);
    }

    @GetMapping("/getAllPost")
    public List<PostSummaryBo> getAllPost(@RequestParam Long pageNumber) {
        List<PostSummaryBo> postSummaryBoList = readService.getAllPost(true, pageNumber);
        return postSummaryBoList;
    }

    @GetMapping("/getChildComments")
    public List<CommentDto> getChildComments(@RequestParam Long commentId,
                                             @RequestParam Long pageNumber) {
        return readService.getChildComments(commentId, pageNumber);
    }
}
