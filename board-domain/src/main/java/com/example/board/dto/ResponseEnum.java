package com.example.board.dto;

public enum ResponseEnum {
    SUCCESS("success"),
    FAIL("fail");

    private final String responseMsg;

    ResponseEnum(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getResponseMsg() {
        return responseMsg;
    }
}
