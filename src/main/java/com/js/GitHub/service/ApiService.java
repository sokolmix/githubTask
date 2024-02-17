package com.js.GitHub.service;

import com.js.GitHub.model.Branch;
import com.js.GitHub.model.GitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiService {

    private final WebClient gitWebClient;
    private static final String REPO_URL = "/users/{user}/repos";
    private static final String BRANCH_URL = "/repos/{user}/{repo}/branches";

    public List<GitRepository> callGitRepositories(String username) {
        log.debug("ApiService | callGitRepositories(String username) for {} ", username);

        return gitWebClient.get()
                .uri(REPO_URL, username)
                .retrieve()
                .bodyToFlux(GitRepository.class)
                .collectList()
                .block();
    }


    public List<Branch> callBranchesApi(GitRepository gitRepository) {
        log.debug("ApiService | callBranchesApi(String username) for user: {}, project: {}", gitRepository.getOwner(), gitRepository.getName());

        return gitWebClient.get()
                .uri(BRANCH_URL, gitRepository.getOwner().getLogin(), gitRepository.getName())
                .retrieve()
                .bodyToFlux(Branch.class)
                .collectList()
                .block();
    }

}
