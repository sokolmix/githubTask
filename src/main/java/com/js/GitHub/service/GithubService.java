package com.js.GitHub.service;

import com.js.GitHub.dto.RepositoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubService {

    private final ApiService apiService;

    public List<RepositoryResponse> getUserRepositories(String username) {
        log.debug("GithubService | getUserRepositories(String username) for {} ", username);
        return apiService.getRepositories(username);
    }
}
