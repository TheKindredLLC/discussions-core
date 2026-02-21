package org.kindredhq.discussions.core.domain.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.serialization.Serializable
import org.kindredhq.discussions.core.domain.enums.ReportStatus

/**
 * Defines filter criteria for querying [Report] entities.
 *
 * All properties are optional unless otherwise specified.
 * A `null` value indicates that the corresponding filter
 * should not be applied.
 *
 * Pagination and ordering semantics are implementation-defined.
 */
@Serializable
@OptIn(ExperimentalUuidApi::class)
data class ReportQuery(
    val discussionId: Uuid? = null,
    val createdBy: String? = null,
    val statuses: Set<ReportStatus>? = null,
    val limit: Int? = null,
)
