package tests.issues;

import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;

import clients.RepoClient;
import io.restassured.response.Response;

public class IssueTests {

    private final RepoClient repoClient = new RepoClient();

    @Test
    void listIssues_returnsArrayWithExpectedShape() {
        Response response = repoClient.listIssues("octocat", "Hello-World");

        response.then()
            .statusCode(200)
            .body("$", isA(List.class));

        if (!response.jsonPath().getList("$").isEmpty()) {
            response.then()
                .body("[0].number", notNullValue())
                .body("[0].title", notNullValue())
                .body("[0].state", anyOf(equalTo("open"), equalTo("closed")));
        }
    }
}
