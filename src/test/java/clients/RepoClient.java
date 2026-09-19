package clients;

import static io.restassured.RestAssured.given;
import config.ApiConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RepoClient {

    private RequestSpecification request() {
        RequestSpecification spec = given()
            .baseUri(ApiConfig.BASE_URL)
            .header("Accept", "application/vnd.github+json")
            .header("User-Agent", "github-api-test-framework");
        if (ApiConfig.TOKEN != null) {
            spec.header("Authorization", "Bearer " + ApiConfig.TOKEN);
        }
        return spec;
    }

    public Response getRepo(String owner, String repo) {
        return request().get("/repos/{owner}/{repo}", owner, repo);
    }

    public Response listIssues(String owner, String repo) {
        return request().get("/repos/{owner}/{repo}/issues", owner, repo);
    }

    public Response getRateLimit() {
        return request().get("/rate_limit");
    }
}
