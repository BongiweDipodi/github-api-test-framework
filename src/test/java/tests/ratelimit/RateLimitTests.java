package tests.ratelimit;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.jupiter.api.Test;

import clients.RepoClient;
import io.restassured.response.Response;

public class RateLimitTests {
    private final RepoClient repoClient = new RepoClient();

    private void assumeGitHubAvailable(Response response) {
        assumeTrue(
            response.getStatusCode() < 500,
            "GitHub API is currently unavailable (status " + response.getStatusCode() + "); skipping live API test."
        );
    }

    @Test
    void getRateLimit_returnsCoreRateLimitFields() {
        Response response = repoClient.getRateLimit();
        assumeGitHubAvailable(response);

        response.then()
            .statusCode(200)
            .body("rate.limit", notNullValue())
            .body("rate.remaining", notNullValue())
            .body("rate.reset", notNullValue())
            .body("rate.used", notNullValue());
    }
}
