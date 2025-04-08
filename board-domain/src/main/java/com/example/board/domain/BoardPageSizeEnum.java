package com.example.board.domain;

public enum BoardPageSizeEnum {
    POST_DEFAULT_SIZE(20),
    COMMENT_DEFAULT_SIZE(20);

    private final int size;

    BoardPageSizeEnum(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
