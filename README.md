# kindred-discussions-core

[![Kotlin Version](https://img.shields.io/badge/kotlin-2.3.10-blue.svg)](https://kotlinlang.org/)
[![License](https://img.shields.io/badge/license-Apache%20License%202.0-blue)](LICENSE)
[![Platform](https://img.shields.io/badge/platform-jvm-brightgreen.svg)](https://www.oracle.com/java/)

A framework-neutral domain core library for building discussion and reporting systems in Kotlin.

## Purpose

`kindred-discussions-core` provides the foundational domain models, DTOs, and repository contracts required to implement threaded discussions and user reports. It is designed to be highly portable and unopinionated about your infrastructure, logging, or specific business policies.

## Philosophy

- **Domain-Only**: This library contains only domain logic and models. It does not include persistence implementations, networking, or logging.
- **Framework-Neutral**: No dependency on Spring, Ktor, Micronaut, or any other framework.
- **No Side Effects**: The library provides validators and utility functions but does not enforce business policy or service-layer logic.
- **Explicit API**: Designed with Kotlin's `explicitApi=strict` for clear and stable library boundaries.

## Installation

Add the dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("org.kindredhq:kindred-discussions-core:0.1.0")
}
```

## Experimental UUID API

This library uses the new [Kotlin UUID API](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.uuid/-uuid/). Since this API is currently experimental, you will need to opt-in:

```kotlin
@OptIn(kotlin.uuid.ExperimentalUuidApi::class)
fun main() {
    // your code
}
```

Or in your Gradle configuration:

```kotlin
kotlin {
    compilerOptions {
        freeCompilerArgs.add("-opt-in=kotlin.uuid.ExperimentalUuidApi")
    }
}
```

## Example Usage

### Creating a Discussion

```kotlin
import org.kindredhq.discussions.core.domain.model.Discussion
import org.kindredhq.discussions.core.domain.ids.DiscussionIds
import org.kindredhq.discussions.core.domain.enums.DiscussionsStatus
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
val discussion = Discussion(
    id = DiscussionIds.newId(),
    userId = "user-123",
    body = "Hello, world!",
    createdAt = Instant.parse("2026-02-22T15:00:00Z"),
    status = DiscussionsStatus.VISIBLE
)
```

### Using DiscussionValidator

The library provides structural validation for incoming DTOs.

```kotlin
import org.kindredhq.discussions.core.validation.DiscussionValidator
import org.kindredhq.discussions.core.dto.request.DiscussionCreateRequest
import org.kindredhq.discussions.core.domain.model.DiscussionValidationRules
import org.kindredhq.discussions.core.domain.exceptions.ValidationException

val request = DiscussionCreateRequest(
    userId = "user-123",
    body = "   " // Blank body
)

try {
    DiscussionValidator.validateCreate(request, DiscussionValidationRules(maxBodyLength = 1000))
} catch (e: ValidationException) {
    e.errors.forEach { println("${it.field}: ${it.message}") }
}
```

### Implementing DiscussionRepository

Define your persistence layer by implementing the provided interface.

```kotlin
import org.kindredhq.discussions.core.repository.DiscussionRepository
import org.kindredhq.discussions.core.domain.model.Discussion
import kotlin.uuid.Uuid
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class MySqlDiscussionRepository : DiscussionRepository {
    override suspend fun insert(discussion: Discussion): Discussion {
        // Implementation for saving to MySQL
        return discussion
    }

    override suspend fun findById(id: Uuid): Discussion? {
        // Implementation for finding by ID
        return null
    }
    
    // ... implement other methods
}
```

## Domain vs Service Responsibilities

To maintain a clean architecture, we recommend following these guidelines:

| Responsibility | Handled By |
| :--- | :--- |
| **Structural Validation** | This Library (`DiscussionValidator`) |
| **Domain Models & DTOs** | This Library |
| **Repository Contracts** | This Library |
| **Business Policy (e.g. "Only mods can pin")** | Your Service Layer |
| **Authorization/AuthN** | Your Service Layer |
| **Persistence (SQL/NoSQL)** | Your Infrastructure Layer |
| **Logging & Monitoring** | Your Infrastructure Layer |

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
A short explanation: This project also references Apache-2.0 compatibility for certain components where noted.
