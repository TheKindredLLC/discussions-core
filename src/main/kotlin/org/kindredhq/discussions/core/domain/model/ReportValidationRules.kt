package org.kindredhq.discussions.core.domain.model

private const val MAX_REPORT_REASON_LENGTH = 1000
/**
 * Configuration rules for structural validation of [Report] requests.
 *
 * This class allows implementing services to customize validation
 * constraints enforced by [ReportValidator].
 *
 * These rules apply only to structural constraints such as required
 * fields and maximum length limits. Business rules, duplicate-report
 * prevention, and target existence checks remain the responsibility
 * of the implementing service.
 *
 * @property requireReason Whether a non-blank reason is required.
 * @property maxReasonLength Maximum allowed length of the report reason.
 */
public data class ReportValidationRules(
    public val requireReason: Boolean = true,
    public val maxReasonLength: Int = MAX_REPORT_REASON_LENGTH,
) {
    init {
        require(maxReasonLength > 0) {
            "maxReasonLength must be greater than zero."
        }
    }
}
