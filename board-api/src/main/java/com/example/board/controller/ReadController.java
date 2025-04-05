package com.example.board.controller;

import com.example.board.bo.PostSummaryBo;
import com.example.board.dto.CommentDto;
import com.example.board.dto.PostDto;
import com.example.board.service.ReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class ReadController {

    private final ReadService readService;

    @GetMapping("/getPostById")
    public ResponseEntity<PostDto> getPostById(@RequestParam Long id) {
        PostDto postById = readService.getPostById(id);
        if (postById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(postById);
    }

    @GetMapping("/getAllPost")
    public ResponseEntity<List<PostSummaryBo>> getAllPost(@RequestParam Long pageNumber) {
        List<PostSummaryBo> postSummaryBoList = readService.getAllPost(true, pageNumber);
        if (postSummaryBoList == null || postSummaryBoList.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(postSummaryBoList);
    }

    @GetMapping("/getChildComments")
    public ResponseEntity<List<CommentDto>> getChildComments(@RequestParam Long commentId,
                                             @RequestParam Long pageNumber) {
        List<CommentDto> childComments = readService.getChildComments(commentId, pageNumber);
        if (childComments == null || childComments.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(childComments, HttpStatus.OK);
        }
    }
}
