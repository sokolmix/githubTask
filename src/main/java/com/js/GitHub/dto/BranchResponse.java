package com.js.GitHub.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class BranchResponse {
    private String branchName;
    private String lastCommitSha;
}
