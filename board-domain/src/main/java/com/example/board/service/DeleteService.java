package com.example.board.service;

import com.example.board.repository.RepositoryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteService {

    private final RepositoryWrapper repositoryWrapper;

    public boolean softDeletePost(Long id) {
        return repositoryWrapper.softDeletePost(id);
    }

    public boolean hardDeletePost(Long id) {
        return repositoryWrapper.hardDeletePost(id);
    }
}
