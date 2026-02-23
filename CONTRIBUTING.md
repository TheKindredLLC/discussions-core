# Contributing to kindred-discussions-core

Thank you for your interest in contributing to `kindred-discussions-core`! We welcome contributions from the community.

## Development Setup

1. **JDK**: Ensure you have JDK 17 or higher installed (Targeting Kotlin JVM).
2. **Clone the repository**:
   ```bash
   git clone https://github.com/kindredhq/kindred-discussions-core.git
   cd kindred-discussions-core
   ```
3. **Build**:
   ```bash
   ./gradlew build
   ```

## Kotlin Explicit API

This project uses `explicitApi=strict` mode. This means:
- All public declarations must have an explicit visibility modifier (`public`, `internal`, `private`).
- All public declarations must have an explicit return type.

This ensures that we don't accidentally expose internal implementation details and maintains a stable API for library users.

## Style Rules

We use **detekt** and **spotless** to maintain code quality and consistent formatting.

- **Spotless**: Enforces [ktlint](https://pinterest.github.io/ktlint/) rules.
- **Detekt**: Performs static code analysis to find potential bugs and code smells.

To check and apply formatting:
```bash
./gradlew spotlessCheck
./gradlew spotlessApply
./gradlew detekt
```

## Testing Expectations

- All new features or bug fixes should include unit tests.
- We use Kotlin Test for our test suite.
- Tests should be placed in `src/test/kotlin`.
- Aim for high coverage of domain logic and validation rules.

## Binary Compatibility Awareness

As a library, we strive to maintain binary compatibility within major versions.
- Avoid breaking changes in public interfaces.
- Use `@Deprecated` with `ReplaceWith` when evolving the API.
- Be mindful of the `ExperimentalUuidApi` usage; changes in the underlying Kotlin API may affect our library.

## Submitting Changes

1. Create a new branch for your feature or fix.
2. Ensure all tests pass and linting is green.
3. Submit a Pull Request with a clear description of the changes and the problem they solve.
