package com.example.board.bo;

import lombok.Getter;
import org.springframework.data.domain.Sort;

public enum BoardPageSortEnum {

    CREATED_DATE_ASC("createdDate", Sort.by( "createdAt").ascending()),
    UPDATED_DATE_ASC("updatedAt", Sort.by( "updatedAt").ascending()),

    ;

    @Getter
    private final String sortColumn;
    @Getter
    private final Sort sort;

    BoardPageSortEnum(String sortColumn, Sort sort) {
        this.sortColumn = sortColumn;
        this.sort = sort;
    }
}
