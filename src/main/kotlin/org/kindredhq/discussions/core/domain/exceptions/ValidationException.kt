package org.kindredhq.discussions.core.domain.exceptions

/**
 * Exception thrown when structural validation of a request fails.
 *
 * This exception indicates that one or more structural invariants
 * were violated during validation of a create or update operation.
 *
 * Validation performed by this library is limited to structural
 * constraints such as required fields, length limits, and basic
 * format checks. Business rules, authorization checks, and
 * cross-entity validation are the responsibility of the
 * implementing service layer.
 *
 * The [errors] property contains one or more [FieldError]
 * instances describing the specific validation failures.
 *
 * Implementing services are responsible for translating this
 * exception into appropriate application- or transport-layer
 * responses.
 *
 * @property errors A list of field-level validation failures.
 */
public class ValidationException(
    val errors: List<FieldError>,
) : DiscussionsCoreException(
        "Validation failed: ${errors.joinToString { it.field }}",
    )
