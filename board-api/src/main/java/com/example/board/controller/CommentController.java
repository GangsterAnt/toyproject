package com.example.board.controller;

import com.example.board.dto.CommentDto;
import com.example.board.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments/{parentCommentId}/replies")
    public ResponseEntity<List<CommentDto>> getChildComments(@PathVariable Long parentCommentId,
                                                             @RequestParam Long pageNumber) {
        List<CommentDto> childComments = commentService.getChildComments(parentCommentId, pageNumber);
        if (childComments == null || childComments.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(childComments, HttpStatus.OK);
        }
    }
}
