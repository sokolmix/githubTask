package com.js.GitHub.controller;

import com.js.GitHub.dto.RepositoryResponse;
import com.js.GitHub.service.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/github/api")
@RequiredArgsConstructor
public class GithubController {
    private final GithubService githubService;

    @GetMapping(
            value = "/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<RepositoryResponse> getUserRepositories(@PathVariable String username) {
        return githubService.getUserRepositories(username);
    }


}
