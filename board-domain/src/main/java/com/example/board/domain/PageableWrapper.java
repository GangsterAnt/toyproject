package com.example.board.domain;


import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Getter
public class PageableWrapper {

    private final Pageable pageable;

    public PageableWrapper(int pageNumber, BoardPageSizeEnum pageSizeEnum, BoardPageSortEnum sortEnum) {
        this.pageable = PageRequest.of(pageNumber, pageSizeEnum.getSize(), sortEnum.getSort());
    }
}
