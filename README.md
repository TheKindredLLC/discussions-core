# discussions-core

`discussions-core` is the domain contract library for The Kindred ecosystem’s threaded discussion system.

It provides a framework-agnostic, persistence-agnostic core model that can be reused across services and clients.

---

## Purpose

This library defines the canonical discussion and report domain model used by:

- discussions-service (microservice implementation)
- sorenkai-backend (microservice client)
- Future Android, web, and iOS clients

It exists to enforce clean boundaries and prevent infrastructure leakage into domain logic.

---

## What It Contains

- Domain models (`Discussion`, `Message`, `Report`, etc.)
- Value objects and ID types
- DTOs for transport
- Repository interfaces (contracts only)
- Validation rules and invariants

---

## What It Explicitly Does Not Contain

- Database access
- Exposed or any ORM
- Ktor or any web framework
- Logging
- Metrics
- Infrastructure concerns
- Feed, engagement, or ranking logic

This module is pure domain and contract definition.

---

## Design Principles

- Explicit API mode enabled
- Strict separation of domain and infrastructure
- JVM-first, multiplatform-friendly
- Kotlin 2.3 + JDK 25
- Uses `kotlin.uuid.Uuid` from the Kotlin standard library
- CI-enforced linting and static analysis

---

## Publishing

Planned distribution: Maven Central

Artifact coordinates:

```
org.kindredhq.discussions:discussions-core
```

---

## License

MIT License  
Copyright © 2025 TheKindredLLC
