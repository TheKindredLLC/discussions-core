package org.kindredhq.discussions.core.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.kindredhq.discussions.core.domain.enums.ReportStatus
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

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
 *
 * This model does not enforce state transition rules.
 */
@OptIn(ExperimentalUuidApi::class)
@Serializable
public data class Report(
    @SerialName("id") val id: Uuid,
    @SerialName("target_id") val targetId: Uuid,
    @SerialName("reason") val reason: String,
    @SerialName("created_at") val createdAt: Instant,
    @SerialName("created_by") val createdBy: String,
    @SerialName("status") val status: ReportStatus = ReportStatus.OPEN,
)
