package org.kindredhq.discussions.core.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.kindredhq.discussions.core.domain.enums.ReportStatus
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Defines filter criteria for querying [Report] entities.
 *
 * All properties are optional unless otherwise specified.
 * A `null` value indicates that the corresponding filter
 * should not be applied.
 *
 * Pagination and ordering semantics are implementation-defined.
 * Offset-based pagination, cursor pagination, and ordering strategies are not defined by this model.
 */
@Serializable
@OptIn(ExperimentalUuidApi::class)
public data class ReportQuery(
    @SerialName("target_id") val targetId: Uuid? = null,
    @SerialName("created_by") val createdBy: String? = null,
    @SerialName("statuses") val statuses: Set<ReportStatus>? = null,
    @SerialName("limit") val limit: Int? = null,
)
