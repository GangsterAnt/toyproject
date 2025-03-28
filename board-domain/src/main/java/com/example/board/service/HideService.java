package com.example.board.service;

import com.example.board.repository.RepositoryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HideService {

    private final RepositoryWrapper repositoryWrapper;

    public boolean hidePost(Long id) {
        return repositoryWrapper.hidePost(id);
    }

    public boolean unHidePost(Long id) {
        return repositoryWrapper.unHidePost(id);
    }

    public boolean hideComment(Long id) {
        return repositoryWrapper.hideComment(id);
    }

    public boolean unHideComment(Long id) {
        return repositoryWrapper.unHideComment(id);
    }
}
