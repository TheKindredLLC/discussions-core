package org.kindredhq.discussions.core.dto.request

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.serialization.Serializable

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
 * Field constraints such as body length, language format, and existence
 * of parent or target entities must be enforced by the implementing service.
 *
 * @property targetId Optional identifier of the external entity this discussion is associated with.
 * @property parentId Optional identifier of the parent discussion if this is a reply.
 * @property userId Identifier of the author creating the discussion entry.
 * @property body Text content of the discussion entry.
 * @property language Optional BCP 47 language tag associated with the content.
 */
@OptIn(ExperimentalUuidApi::class)
@Serializable
data class DiscussionCreateRequest(
    val targetId: Uuid? = null,
    val parentId: Uuid? = null,
    val userId: String,
    val body: String,
    val language: String? = null,
)
