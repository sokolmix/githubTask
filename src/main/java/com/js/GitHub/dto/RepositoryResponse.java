package com.js.GitHub.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RepositoryResponse {
    private String repoName;
    private String login;
    private List<BranchResponse> branches;

}
