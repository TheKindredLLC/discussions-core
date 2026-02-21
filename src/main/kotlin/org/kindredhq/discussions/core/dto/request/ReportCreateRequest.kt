package org.kindredhq.discussions.core.dto.request

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.serialization.Serializable

/**
 * Request payload for creating a new [Report] against a discussion entry.
 *
 * This DTO represents the client-supplied data required to initiate
 * a report. Reporter identity, validation, and duplicate report
 * handling are the responsibility of the implementing service.
 *
 * ### Target
 * [targetId] identifies the discussion entry being reported.
 * The discussion domain does not validate existence or ownership.
 *
 * ### Moderation Workflow
 * Report lifecycle management (status transitions, review policies,
 * escalation rules, and enforcement actions) is implementation-defined.
 *
 * @property targetId Identifier of the discussion entry being reported.
 * @property reason Text explanation describing the reason for the report.
 */
@OptIn(ExperimentalUuidApi::class)
@Serializable
data class ReportCreateRequest(
    val targetId: Uuid,
    val reason: String,
)
