package com.js.GitHub.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Setter
@Builder
public class BranchResponse {
    private String branchName;
    private String lastCommitSha;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchResponse that = (BranchResponse) o;
        return Objects.equals(branchName, that.branchName) && Objects.equals(lastCommitSha, that.lastCommitSha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchName, lastCommitSha);
    }
}
