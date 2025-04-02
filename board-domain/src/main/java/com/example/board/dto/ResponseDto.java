package com.example.board.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private String responseEnum;
    private Object data;

    public ResponseDto(ResponseEnum responseEnum, Object data) {
        this.responseEnum = responseEnum.getResponseMsg();
        this.data = data;
    }
}
