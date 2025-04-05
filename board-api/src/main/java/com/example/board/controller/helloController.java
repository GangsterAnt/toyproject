package com.example.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, board-api!";
    }

    @GetMapping("/hello/exception")
    public String helloException() {
        throw new IllegalArgumentException("Exception occurred");
    }
}
