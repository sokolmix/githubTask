package com.js.GitHub;

import com.js.GitHub.dto.BranchResponse;
import com.js.GitHub.dto.RepositoryResponse;
import com.js.GitHub.model.Branch;
import com.js.GitHub.model.Commit;
import com.js.GitHub.model.GitRepository;
import com.js.GitHub.model.Owner;
import com.js.GitHub.service.ApiService;
import com.js.GitHub.service.GithubService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class GitHubApplicationTests {

    private final String USER = "testUser";
    private final String REPOSITORY_NAME = "testRepository1";
    private final String REPOSITORY_NAME2 = "testRepository2";
    private final String BRANCH_NAME = "testBranchName";
    private final String SHA = "testSHA";

    @InjectMocks
    private GithubService githubService;
    @Mock
    private ApiService apiServiceMock;


    @Test
    void getUserRepositoriesTest() {
        List<GitRepository> gitRepositoryList = prepareGitRepositoryList();
        when(apiServiceMock.callGitRepositories(USER)).thenReturn(gitRepositoryList);
        when(apiServiceMock.callBranchesApi(gitRepositoryList.get(0))).thenReturn(prepareBranchListForTestRepository());
        when(apiServiceMock.callBranchesApi(gitRepositoryList.get(1))).thenReturn(prepareBranchListForTestRepository());


        List<RepositoryResponse> result = githubService.getUserRepositories(USER);

        Assertions.assertIterableEquals(result, prepareMockedResult());
    }

    private List<RepositoryResponse> prepareMockedResult() {
        return List.of(
                RepositoryResponse.builder()
                        .repoName(REPOSITORY_NAME)
                        .login(USER)
                        .branches(List.of(BranchResponse.builder()
                                .lastCommitSha(SHA)
                                .branchName(BRANCH_NAME)
                                .build()))
                        .build(),
                RepositoryResponse.builder()
                        .repoName(REPOSITORY_NAME2)
                        .login(USER)
                        .branches(List.of(BranchResponse.builder()
                                .lastCommitSha(SHA)
                                .branchName(BRANCH_NAME)
                                .build()))
                        .build()
        );
    }

    private List<Branch> prepareBranchListForTestRepository() {
        Commit commit = new Commit(SHA);
        Branch branch = new Branch(BRANCH_NAME, commit);
        return List.of(branch);
    }


    private List<GitRepository> prepareGitRepositoryList() {
        Owner owner = new Owner(USER);
        GitRepository testRepository1 = new GitRepository(REPOSITORY_NAME, owner, false);
        GitRepository testRepository2 = new GitRepository(REPOSITORY_NAME2, owner, false);
        GitRepository forkTestRepository = new GitRepository("forkTestRepository", owner, true);

        return Arrays.asList(testRepository1, testRepository2, forkTestRepository);
    }

}
