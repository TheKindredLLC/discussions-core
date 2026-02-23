package org.kindredhq.discussions.core.validation

import org.kindredhq.discussions.core.domain.exceptions.FieldError
import org.kindredhq.discussions.core.domain.exceptions.ValidationException
import org.kindredhq.discussions.core.domain.model.ReportValidationRules
import org.kindredhq.discussions.core.dto.request.ReportCreateRequest

/**
 * Performs structural validation for report creation requests.
 *
 * This validator enforces configurable constraints such as
 * required reason and maximum length.
 *
 * Duplicate report prevention, target existence validation,
 * authorization checks, and moderation policies are the
 * responsibility of the implementing service.
 *
 * Validation failures result in a [ValidationException].
 */
public object ReportValidator {
    public fun validateCreate(
        request: ReportCreateRequest,
        rules: ReportValidationRules = ReportValidationRules(),
    ) {
        val errors = mutableListOf<FieldError>()

        val reason = request.reason

        if (rules.requireReason && reason.isBlank()) {
            errors +=
                FieldError(
                    field = "reason",
                    message = "Report reason must not be blank.",
                    code = "report.reason.blank",
                )
        }

        if (reason.length > rules.maxReasonLength) {
            errors +=
                FieldError(
                    field = "reason",
                    message = "Report reason must not exceed ${rules.maxReasonLength} characters.",
                    code = "report.reason.tooLong",
                )
        }

        if (errors.isNotEmpty()) {
            throw ValidationException(errors)
        }
    }
}
