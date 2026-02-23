package org.kindredhq.discussions.core.domain.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the logical lifecycle state of a [Report].
 *
 * This enum models structural workflow state only.
 * Escalation policies, review procedures, resolution rules,
 * and enforcement actions are the responsibility of the implementing service.
 */
@Serializable
public enum class ReportStatus {
    /**
     * The report has been submitted and has not yet been reviewed.
     */
    @SerialName("open")
    OPEN,

    /**
     * The report is currently under review by moderators or automated systems.
     */
    @SerialName("in_review")
    IN_REVIEW,

    /**
     * The report has been reviewed and a decision has been made.
     * The specific outcome is defined by the implementing service.
     */
    @SerialName("resolved")
    RESOLVED,
}
