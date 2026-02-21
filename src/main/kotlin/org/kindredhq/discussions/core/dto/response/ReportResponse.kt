package org.kindredhq.discussions.core.dto.response

import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.serialization.Serializable
import org.kindredhq.discussions.core.domain.enums.ReportStatus

/**
 * Response projection representing a [Report] entity.
 *
 * This DTO exposes the core state of a report as defined by the domain model.
 * Role-based filtering, redaction, and visibility policies are the responsibility
 * of the implementing service.
 *
 * @property id Unique identifier of the report.
 * @property targetId Identifier of the discussion entry being reported.
 * @property reason Text explanation supplied when the report was created.
 * @property createdAt Timestamp when the report was created.
 * @property createdBy Identifier of the reporting user.
 * @property status Current lifecycle status of the report.
 */
@OptIn(ExperimentalUuidApi::class)
@Serializable
data class ReportResponse(
    val id: Uuid,
    val targetId: Uuid,
    val reason: String,
    val createdAt: Instant,
    val createdBy: String,
    val status: ReportStatus,
)
