# Development Guide

This document provides technical details and guidance for developers working on `kindred-discussions-core`.

## Project Structure

- `org.kindredhq.discussions.core.domain`: Core domain models, enums, and exceptions.
- `org.kindredhq.discussions.core.dto`: Data Transfer Objects for requests and responses.
- `org.kindredhq.discussions.core.repository`: Interface contracts for persistence.
- `org.kindredhq.discussions.core.validation`: Logic for structural validation of DTOs.
- `org.kindredhq.discussions.core.domain.ids`: Utilities for ID generation.

## Key Technologies

- **Kotlin 2.3.10**: The primary programming language.
- **kotlinx.serialization**: Used for JSON (and other format) serialization of DTOs and domain models.
- **Kotlin Uuid**: Uses `kotlin.uuid.Uuid` (ExperimentalUuidApi) for all entity identifiers.
- **Kotlin Time**: Uses `kotlin.time.Instant` for timestamps.

## Design Patterns

### Repository Pattern

The library defines `DiscussionRepository` and `ReportRepository` interfaces. These are pure contracts. Implementing services should provide the concrete implementation (e.g., using Exposed, Hibernate, or a NoSQL driver).

### Validator Pattern

Validators are implemented as `object` singletons (e.g., `DiscussionValidator`). They provide `validateCreate` and `validateUpdate` methods that take a DTO and an optional `ValidationRules` object.

## Adding New Features

1.  **Define Domain Models**: Start by defining or updating models in the `domain` package.
2.  **Define DTOs**: Create corresponding requests and response DTOs in the `dto` package. Use `kotlinx.serialization`.
3.  **Update Validators**: If the new feature involves new fields, update the corresponding validator and validation rules.
4.  **Update Repositories**: Add necessary methods to the repository interfaces.
5.  **Write Tests**: Add unit tests in `src/test/kotlin` to verify the new logic.

## Build and Quality Control

The project uses Gradle. Ensure you run the following before submitting any changes:

```bash
./gradlew build
./gradlew spotlessCheck
./gradlew detekt
```

## IDE Configuration

- **IntelliJ IDEA**: Recommended for Kotlin development.
- **Detekt Plugin**: Highly recommended to catch issues in real-time.
- **Spotless**: Can be configured to run on save or via a pre-commit hook.
