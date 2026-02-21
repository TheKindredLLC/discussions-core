package org.kindredhq.discussions.core.domain.enums

import kotlinx.serialization.Serializable

/**
 * Represents the lifecycle state of a [Report].
 *
 * This enum defines logical workflow states only.
 * Escalation policies, review procedures, resolution rules,
 * and enforcement actions are the responsibility of the implementing service.
 */
@Serializable
enum class ReportStatus {

    /**
     * The report has been submitted and has not yet been reviewed.
     */
    OPEN,

    /**
     * The report is currently under review by moderators or automated systems.
     */
    IN_REVIEW,

    /**
     * The report has been reviewed and a decision has been made.
     * The specific outcome is defined by the implementing service.
     */
    RESOLVED,
}
