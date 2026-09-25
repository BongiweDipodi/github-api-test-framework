# github-api-test-framework

A Java test automation framework for the GitHub REST API, built for the Quality Assurance elective at WeThinkCode_.

## What this is

An automated test suite that verifies GitHub's public API behaves the way it's documented to. Instead of manually checking endpoints, these tests make real requests against `api.github.com` and assert on status codes, response shape, and error handling.

## Scope

- **Repos**: fetch a repo, handle a non-existent repo correctly
- **Issues**: list issues on a repo, verify response shape
- **Rate limiting**: verify behavior when limits are hit

## Tech stack

- Java 17
- REST Assured (HTTP client + assertions)
- JUnit 5
- Maven
- GitHub Actions (CI)

## Why it's built this way

- `ApiConfig` isolates the base URL and auth token in one place, so nothing else needs to change if either does.
- Tests are grouped by resource (repos, issues, rate limit) rather than dumped in one file, so it's clear what's covered and what isn't.
- Every test targets one of three things: correct behavior on valid input, sensible failure on invalid input, or a stable response shape (contract).

## Running the tests

```bash
mvn test
```

By default, requests run unauthenticated (60 requests/hour limit from GitHub). To raise that limit, set a personal access token as an environment variable:

```bash
export GITHUB_TOKEN=your_token_here
mvn test
```

No scopes are needed on the token for these tests — public read access is enough.

## CI

Tests run automatically on every push via GitHub Actions (`.github/workflows/ci.yml`).

## Demo
https://youtu.be/xOB64luY66Q

verification code:
WTC-KP7WFVD2
