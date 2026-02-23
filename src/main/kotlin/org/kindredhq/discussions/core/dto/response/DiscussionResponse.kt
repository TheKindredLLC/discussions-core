package org.kindredhq.discussions.core.dto.response

import kotlinx.serialization.Serializable
import org.kindredhq.discussions.core.domain.enums.DiscussionsStatus
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.serialization.SerialName

/**
 * Response projection representing a [Discussion] entity.
 *
 * This DTO exposes the core state of a discussion entry as defined
 * by the domain model. It does not include engagement metrics, enrichment data,
 * moderation metadata, or implementation-specific projections.
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
public data class DiscussionResponse(
    @SerialName("id") val id: Uuid,
    @SerialName("target_id") val targetId: Uuid?,
    @SerialName("parent_id") val parentId: Uuid?,
    @SerialName("user_id") val userId: String,
    @SerialName("body") val body: String,
    @SerialName("language") val language: String?,
    @SerialName("status") val status: DiscussionsStatus,
    @SerialName("is_pinned") val isPinned: Boolean,
    @SerialName("is_locked") val isLocked: Boolean,
    @SerialName("created_at") val createdAt: Instant,
    @SerialName("updated_at") val updatedAt: Instant?,
)
