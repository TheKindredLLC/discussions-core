package org.kindredhq.discussions.core.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Request payload for creating a new [Report] against a target entry.
 * Structural validation (e.g., required reason or length limits) is performed by [ReportValidator].
 *
 * This DTO represents the client-supplied data required to initiate
 * a report. Reporter identity, validation, and duplicate report
 * handling are the responsibility of the implementing service.
 *
 * ### Target
 * [targetId] identifies the entity being reported.
 * The domain does not validate existence or ownership.
 *
 * ### Moderation Workflow
 * Report lifecycle management (status transitions, review policies,
 * escalation rules, and enforcement actions) is implementation-defined.
 *
 * @property targetId Identifier of the target entry being reported.
 * @property reason Text explanation describing the reason for the report.
 */
@OptIn(ExperimentalUuidApi::class)
@Serializable
public data class ReportCreateRequest(
    @SerialName("target_id") val targetId: Uuid,
    @SerialName("reason") val reason: String,
)
