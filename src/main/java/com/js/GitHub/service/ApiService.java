package com.js.GitHub.service;

import com.js.GitHub.dto.BranchResponse;
import com.js.GitHub.dto.RepositoryResponse;
import com.js.GitHub.model.Branch;
import com.js.GitHub.model.GitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiService {

    private final WebClient gitWebClient;
    private static final String REPO_URL = "/users/{user}/repos";
    private static final String BRANCH_URL = "/repos/{user}/{repo}/branches";

    public List<RepositoryResponse> getRepositories(String username) {
        log.debug("ApiService | getRepositories(String username) for {} ", username);

        List<GitRepository> gitRepositoryList = callGitRepositories(username);

        List<GitRepository> notForkGitRepository =
                gitRepositoryList.stream()
                        .filter(e -> !e.isFork())
                        .collect(Collectors.toList());

        log.info("Found {} not forks repositories for {}", notForkGitRepository.size(), username);

        return getResponse(notForkGitRepository);
    }

    private List<RepositoryResponse> getResponse(List<GitRepository> gitRepository) {
        List<RepositoryResponse> response = new ArrayList<>();

        gitRepository.forEach(e -> {
            List<BranchResponse> branchResponses = getBranchesResponses(e);

            response.add(
                    RepositoryResponse.builder()
                            .branches(branchResponses)
                            .login(e.getOwner().getLogin())
                            .repoName(e.getName())
                            .build()
            );
        });

        return response;
    }

    private List<GitRepository> callGitRepositories(String username) {
        return gitWebClient.get()
                .uri(REPO_URL, username)
                .retrieve()
                .bodyToFlux(GitRepository.class)
                .collectList()
                .block();
    }

    private List<BranchResponse> getBranchesResponses(GitRepository gitRepository) {
        List<Branch> branches = callBranchesApi(gitRepository);

        if (branches == null) {
            log.info("No branches were found for user: {}, repository name: {}", gitRepository.getOwner(), gitRepository.getName());
            return null;
        }

        return branches.stream()
                .map(this::mapToBranchResponse)
                .collect(Collectors.toList());
    }

    private List<Branch> callBranchesApi(GitRepository gitRepository) {
        return gitWebClient.get()
                .uri(BRANCH_URL, gitRepository.getOwner().getLogin(), gitRepository.getName())
                .retrieve()
                .bodyToFlux(Branch.class)
                .collectList()
                .block();
    }

    private BranchResponse mapToBranchResponse(Branch branch) {
        return BranchResponse.builder()
                .branchName(branch.getName())
                .lastCommitSha(branch.getCommit().getSha())
                .build();
    }

}
