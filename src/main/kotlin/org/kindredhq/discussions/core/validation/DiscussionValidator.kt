package org.kindredhq.discussions.core.validation

import org.kindredhq.discussions.core.domain.exceptions.FieldError
import org.kindredhq.discussions.core.domain.exceptions.ValidationException
import org.kindredhq.discussions.core.domain.model.DiscussionValidationRules
import org.kindredhq.discussions.core.dto.common.DiscussionUpdateRequest
import org.kindredhq.discussions.core.dto.request.DiscussionCreateRequest

/**
 * Performs structural validation for discussion create and update requests.
 *
 * This validator enforces configurable structural constraints such as
 * required fields and length limits.
 *
 * Business rules, authorization checks, parent existence validation,
 * and moderation policies are the responsibility of the implementing service.
 *
 * Validation failures result in a [ValidationException].
 */
public object DiscussionValidator {
    public fun validateCreate(
        request: DiscussionCreateRequest,
        rules: DiscussionValidationRules = DiscussionValidationRules(),
    ) {
        val errors = mutableListOf<FieldError>()

        // userId must not be blank
        if (request.userId.isBlank()) {
            errors +=
                FieldError(
                    field = "user_id",
                    message = "User identifier must not be blank.",
                    code = "discussion.userId.blank",
                )
        }

        // body must not be blank
        if (request.body.isBlank()) {
            errors +=
                FieldError(
                    field = "body",
                    message = "Discussion body must not be blank.",
                    code = "discussion.body.blank",
                )
        }

        // body length
        if (request.body.length > rules.maxBodyLength) {
            errors +=
                FieldError(
                    field = "body",
                    message = "Discussion body must not exceed ${rules.maxBodyLength} characters.",
                    code = "discussion.body.tooLong",
                )
        }

        // optional language validation
        if (rules.requireLanguage && request.language.isNullOrBlank()) {
            errors +=
                FieldError(
                    field = "language",
                    message = "Language must be provided.",
                    code = "discussion.language.required",
                )
        }

        if (errors.isNotEmpty()) {
            throw ValidationException(errors)
        }
    }

    public fun validateUpdate(
        request: DiscussionUpdateRequest,
        rules: DiscussionValidationRules = DiscussionValidationRules(),
    ) {
        val errors = mutableListOf<FieldError>()

        val body = request.body

        if (body.isBlank()) {
            errors +=
                FieldError(
                    field = "body",
                    message = "Discussion body must not be blank.",
                    code = "discussion.body.blank",
                )
        }

        if (body.length > rules.maxBodyLength) {
            errors +=
                FieldError(
                    field = "body",
                    message = "Discussion body must not exceed ${rules.maxBodyLength} characters.",
                    code = "discussion.body.tooLong",
                )
        }

        if (errors.isNotEmpty()) {
            throw ValidationException(errors)
        }
    }
}
