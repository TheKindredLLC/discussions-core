package org.kindredhq.discussions.core.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Request payload for creating a new [Discussion].
 *
 * This DTO represents the client-supplied fields required to create
 * a discussion entry. Identity, timestamps, and moderation state
 * are assigned by the implementing service.
 *
 * ### Threading
 * - If [parentId] is `null`, the discussion is considered a root-level entry.
 * - If [parentId] is non-null, the discussion is treated as a reply to
 *   an existing discussion entry.
 *
 * ### Target Association
 * [targetId] optionally associates the discussion with an external
 * domain entity (for example, a writing or topic). The discussion
 * domain does not own or validate this identifier.
 *
 * ### Identity
 * [userId] represents the author identifier and is implementation-defined.
 * Authentication and authorization are not enforced by this library.
 *
 * ### Validation
 * Structural validation (e.g., body length or required fields) is performed by [DiscussionValidator].
 * Cross-entity validation, authorization checks, and existence checks for parent or target entities are the
 * responsibility of the implementing service.
 *
 * @property targetId Optional identifier of the external entity this discussion is associated with.
 * @property parentId Optional identifier of the parent discussion if this is a reply.
 * @property userId Identifier of the author creating the discussion entry.
 * @property body Text content of the discussion entry.
 * @property language Optional BCP 47 language tag associated with the content.
 */
@OptIn(ExperimentalUuidApi::class)
@Serializable
public data class DiscussionCreateRequest(
    @SerialName("target_id") val targetId: Uuid? = null,
    @SerialName("parent_id") val parentId: Uuid? = null,
    @SerialName("user_id") val userId: String,
    @SerialName("body") val body: String,
    @SerialName("language") val language: String? = null,
)
