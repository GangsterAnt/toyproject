package com.example.board.dto;

import lombok.Getter;

@Getter
public enum ResponseEnum { //TODO 쓸까 말까?
    SUCCESS("success"),
    FAIL("fail"),
    NO_POST("no post"),
    NO_COMMENT("no comment")
;

    private final String responseMsg;

    ResponseEnum(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
