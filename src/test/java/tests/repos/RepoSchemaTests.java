package tests.repos;

import clients.RepoClient;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RepoSchemaTests {

    private final RepoClient repoClient = new RepoClient();

    @Test
    void getRepo_matchesExpectedSchema() {
        Response response = repoClient.getRepo("octocat", "Hello-World");
        assumeTrue(
            response.getStatusCode() < 500,
            "GitHub API is currently unavailable (status " + response.getStatusCode() + "); skipping live API test."
        );

        response.then()
            .body(matchesJsonSchemaInClasspath("schemas/repo-schema.json"));
    }
}
