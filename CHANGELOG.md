# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [0.1.0] - 2026-02-22

### Added
- Initial release of `kindred-discussions-core`.
- Domain models for `Discussion` and `Report`.
- DTOs for creation, update, and response payloads.
- Repository contracts for `DiscussionRepository` and `ReportRepository`.
- Structural validators: `DiscussionValidator` and `ReportValidator`.
- Domain-specific exceptions including `ValidationException` and `DiscussionsCoreException`.
- UUID generation utility using `kotlin.uuid.Uuid`.
- Integration with `kotlinx.serialization`.
- Enabled `explicitApi=strict`.
