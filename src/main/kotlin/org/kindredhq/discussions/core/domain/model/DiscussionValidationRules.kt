package org.kindredhq.discussions.core.domain.model

private const val MAX_DISCUSSION_BODY_LENGTH = 5000

/**
 * Configuration rules for structural validation of [Discussion] requests.
 *
 * This class allows implementing services to customize validation
 * constraints enforced by [DiscussionValidator].
 *
 * These rules apply only to structural constraints such as length
 * limits and required fields. Business rules, authorization policies,
 * and workflow rules remain the responsibility of the implementing service.
 *
 * @property maxBodyLength Maximum allowed length of the discussion body.
 * @property requireLanguage Whether a non-blank language tag is required.
 */
public data class DiscussionValidationRules(
    public val maxBodyLength: Int = MAX_DISCUSSION_BODY_LENGTH,
    public val requireLanguage: Boolean = false,
) {
    init {
        require(maxBodyLength > 0) {
            "maxBodyLength must be greater than zero."
        }
    }
}
