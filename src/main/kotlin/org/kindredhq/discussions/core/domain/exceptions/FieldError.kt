package org.kindredhq.discussions.core.domain.exceptions

/**
 * Represents a single field-level validation failure.
 *
 * Instances of this class are typically included in a
 * [ValidationException] to describe one or more structural
 * validation errors.
 *
 * This model is transport-neutral. Implementing services are
 * responsible for translating field errors into appropriate
 * response formats (e.g., HTTP error responses, GraphQL errors,
 * UI validation messages).
 *
 * @property field The name of the field associated with the validation failure.
 *                 Field naming conventions are implementation-defined.
 * @property message Human-readable description of the validation error.
 * @property code Optional machine-readable error code that can be used
 *                for programmatic handling or internationalization.
 */
data class FieldError(
    val field: String,
    val message: String,
    val code: String? = null,
)
