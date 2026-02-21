package org.kindredhq.discussions.core.domain.model

import org.kindredhq.discussions.core.util.MAX_DISCUSSION_BODY_LENGTH

data class DiscussionValidationRules(
    val maxBodyLength: Int = MAX_DISCUSSION_BODY_LENGTH,
    val allowBlankLanguage: Boolean = true,
)
