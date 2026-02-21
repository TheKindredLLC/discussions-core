package org.kindredhq.discussions.core.dto.response

import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.serialization.Serializable
import org.kindredhq.discussions.core.domain.enums.DiscussionsStatus

/**
 * Response projection representing a [Discussion] entity.
 *
 * This DTO exposes the core state of a discussion entry as defined
 * by the domain model. It does not include engagement metrics,
 * author profile enrichment, or implementation-specific projections.
 *
 * Visibility rules, filtering behavior, and authorization checks
 * are the responsibility of the implementing service.
 *
 * ### Threading
 * - If [parentId] is `null`, this represents a root-level discussion entry.
 * - If [parentId] is non-null, this represents a reply to another discussion.
 *
 * ### Target Association
 * [targetId] optionally associates the discussion with an external entity.
 * Interpretation and validation of this identifier are implementation-defined.
 *
 * ### Moderation State
 * [status] reflects the moderation or visibility state of the discussion.
 * Services may filter or transform responses based on this value.
 *
 * @property id Unique identifier of the discussion entry.
 * @property targetId Optional identifier of the associated external entity.
 * @property parentId Optional identifier of the parent discussion entry.
 * @property userId Identifier of the author.
 * @property body Text content of the discussion entry.
 * @property language Optional BCP 47 language tag associated with the content.
 * @property status Moderation and visibility state.
 * @property isPinned Indicates whether this discussion is pinned within its context.
 * @property isLocked Indicates whether replies are disabled.
 * @property createdAt Timestamp indicating when the discussion was created.
 * @property updatedAt Timestamp of the most recent content update, if applicable.
 */
@OptIn(ExperimentalUuidApi::class)
@Serializable
data class DiscussionResponse(
    val id: Uuid,
    val targetId: Uuid?,
    val parentId: Uuid?,
    val userId: String,
    val body: String,
    val language: String?,
    val status: DiscussionsStatus,
    val isPinned: Boolean,
    val isLocked: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant?,
)
