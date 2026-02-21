package org.kindredhq.discussions.core.domain.model

import org.kindredhq.discussions.core.domain.enums.ReportStatus
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.serialization.Serializable

/**
 * Represents a report submitted against a discussion or other target entity.
 *
 * A [Report] describes a claim made by a user regarding inappropriate or
 * policy-violating content. This model defines report structure only.
 *
 * Moderation workflows, escalation policies, retention rules,
 * and enforcement behavior are implementation concerns of the consuming service.
 *
 * @property id Unique identifier for this report.
 * @property targetId Identifier of the specific entity being reported.
 * @property reason Free-form reason supplied by the reporting user.
 * @property createdAt Timestamp when the report was created.
 * @property createdBy Identifier of the user who submitted the report.
 * @property status Current lifecycle state of the report.
 */
@OptIn(ExperimentalUuidApi::class)
@Serializable
data class Report
    constructor(
        val id: Uuid,
        val targetId: Uuid,
        val reason: String,
        val createdAt: Instant,
        val createdBy: String,
        val status: ReportStatus = ReportStatus.OPEN,
    )
