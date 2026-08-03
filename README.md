# Enterprise Access Platform

A single sign-on and rate-limiting platform — the "front door" of a company. Users prove
who they are once (with a password, or via Google, Microsoft Entra ID, Okta, or SAML),
and every downstream tool trusts that proof.

Built as a long-form learning project, one phase at a time.

## What it does

Every request that reaches this platform has to answer four questions:

| Question | Answered by |
|---|---|
| Who are you? | Authentication — Amazon Cognito |
| What may you do? | Authorization — roles and permissions in PostgreSQL |
| Are you behaving? | Rate limiting — token buckets in Redis |
| Is the system healthy? | Circuit breakers and monitoring |

## Tech stack

**Backend** — Java 21, Spring Boot, Spring Security, Spring Data JPA, Thymeleaf, Maven
**Identity** — Amazon Cognito (federated to Google / Microsoft / Okta / SAML)
**Data** — PostgreSQL with Flyway migrations, Redis for counters and circuit state
**Infrastructure** — Docker, ECS Fargate, Terraform, GitHub Actions

## Repository layout

```
.
├── UI/           Hand-built HTML pages (landing, signup, SSO login)
└── Auth/         Spring Boot backend
```

The pages in `UI/` move into `Auth/src/main/resources/templates` in Phase 3, where they
get served at clean routes (`/`, `/signup`, `/login`, `/dashboard`) instead of `.html` files.

## Status

Milestone 1 — Foundation, in progress.

- [x] Three UI pages built, with backend hook points marked
- [x] Git repository initialized
- [x] Maven project builds and runs
- [x] `/actuator/health` responding
- [ ] Pages served at clean Spring routes
- [ ] Spring Security with in-memory users

Later milestones: real identity with Cognito, Redis rate limiting, the four SSO providers,
multi-tenant data and permissions, vendor resilience, and production deployment on AWS.

## Running it locally

Requires JDK 21. Maven is not needed — the project ships with the Maven wrapper.

```
cd Auth
./mvnw spring-boot:run        # Windows: .\mvnw.cmd spring-boot:run
```

The app serves on `http://localhost:8080`. Verify it is alive:

```
curl http://localhost:8080/actuator/health     # {"status":"UP"}
```

## Security ground rules

These are non-negotiable in this codebase:

1. Passwords live only in Cognito — never in PostgreSQL, Redis, logs, or code.
2. Never log tokens, secrets, authorization codes, or session cookies.
3. Validate every JWT fully: signature, expiry, issuer, audience.
4. Login errors never reveal whether an account exists.
5. Every tenant-owned query filters by `organization_id` — no exceptions.
6. Validate all external input server-side; client-side checks are cosmetic.
7. Secrets come from environment variables locally and Secrets Manager in AWS — never Git.
8. Deny by default: routes are protected unless explicitly made public.
9. Use `state`, `nonce`, and PKCE on every OAuth flow.
10. Redis is never the system of record; the app must survive Redis being empty.
11. Rate limits and lockouts expire on their own — no permanent locks from anonymous traffic.
12. The app container runs as a non-root user, with no secrets baked into the image.

## Development workflow

- `main` is always working — never commit broken code to it.
- One branch per phase: `phase-02-foundation`, `phase-08-signup`, and so on.
- Small commits with meaningful messages.
- Each phase ends with the app running and tests passing.
