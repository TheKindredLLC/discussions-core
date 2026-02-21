package org.kindredhq.discussions.core.validation

import org.kindredhq.discussions.core.domain.model.DiscussionValidationRules
import org.kindredhq.discussions.core.dto.request.DiscussionCreateRequest

object DiscussionValidator {

    fun validateCreate(
        request: DiscussionCreateRequest,
        rules: DiscussionValidationRules = DiscussionValidationRules(),
    ) {
    }
}
