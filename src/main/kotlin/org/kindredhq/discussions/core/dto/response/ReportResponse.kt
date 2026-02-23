package org.kindredhq.discussions.core.dto.response

import kotlinx.serialization.Serializable
import org.kindredhq.discussions.core.domain.enums.ReportStatus
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.serialization.SerialName

/**
 * Response projection representing a [Report] entity.
 *
 * This DTO exposes the core state of a report as defined by the domain model.
 * Role-based filtering, redaction, and visibility policies are the responsibility
 * of the implementing service.
 *
 * @property id Unique identifier of the report.
 * @property targetId Identifier of the target entry being reported.
 * @property reason Text explanation supplied when the report was created.
 * @property createdAt Timestamp when the report was created.
 * @property createdBy Identifier of the reporting user.
 * @property status Current lifecycle status of the report.
 *
 * This projection does not enforce role-based redaction of reporter identity.
 */
@OptIn(ExperimentalUuidApi::class)
@Serializable
public data class ReportResponse(
    @SerialName("id") val id: Uuid,
    @SerialName("target_id") val targetId: Uuid,
    @SerialName("reason") val reason: String,
    @SerialName("created_at") val createdAt: Instant,
    @SerialName("created_by") val createdBy: String,
    @SerialName("status") val status: ReportStatus,
)
