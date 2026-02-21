package org.kindredhq.discussions.core.domain.exceptions

/**
 * Base exception type for all errors originating from the
 * discussions core domain.
 *
 * This exception represents domain-level failures and is intended
 * to be extended by more specific exception types such as
 * [ValidationException].
 *
 * The library does not define transport semantics (e.g., HTTP status
 * codes or error response formats). Implementing services are
 * responsible for translating this exception into appropriate
 * application- or transport-layer responses.
 *
 * This exception type is framework-neutral and contains no
 * infrastructure-specific behavior.
 *
 * @param message Human-readable description of the failure.
 * @param cause Optional underlying cause of the failure.
 */
open class DiscussionsCoreException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause)
