package test.java.tests.repos;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import test.java.clients.RepoClient;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class RepoTests {
    private final RepoClient repoClient = new RepoClient();

    private void assumeGitHubAvailable(Response response) {
        assumeTrue(
            response.getStatusCode() < 500,
            "GitHub API is currently unavailable (status " + response.getStatusCode() + "); skipping live API test."
        );
    }

    @Test
    void getRepo_returnsExpectedFields() {
        Response response = repoClient.getRepo("octocat", "Hello-World");
        assumeGitHubAvailable(response);

        response.then()
            .statusCode(200)
            .body("name", equalTo("Hello-World"))
            .body("owner.login", equalTo("octocat"))
            .body("private", equalTo(false))
            .body("id", notNullValue());
    }

    @Test
    void getRepo_nonExistentRepo_returns404() {
        Response response = repoClient.getRepo("octocat", "this-repo-does-not-exist-12345");
        assumeGitHubAvailable(response);

        response.then()
            .statusCode(404)
            .body("message", equalTo("Not Found"));
    }
}
