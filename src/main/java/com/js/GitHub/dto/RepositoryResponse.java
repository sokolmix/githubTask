package com.js.GitHub.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
public class RepositoryResponse {
    private String repoName;
    private String login;
    private List<BranchResponse> branches;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepositoryResponse that = (RepositoryResponse) o;
        return Objects.equals(repoName, that.repoName) && Objects.equals(login, that.login) && Objects.equals(branches, that.branches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repoName, login, branches);
    }
}
