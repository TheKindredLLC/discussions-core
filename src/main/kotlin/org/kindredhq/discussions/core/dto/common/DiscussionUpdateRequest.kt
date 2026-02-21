package org.kindredhq.discussions.core.dto.common

import kotlinx.serialization.Serializable

/**
 * Request payload for updating an existing [Discussion].
 *
 * This DTO represents the client-supplied fields that may be modified
 * after a discussion entry has been created.
 *
 * Identity, authorization, moderation state, and timestamp management
 * are the responsibility of the implementing service.
 *
 * Implementations may enforce:
 * - Author-only edit restrictions
 * - Edit windows or time limits
 * - Content validation rules
 * - Moderation policies
 *
 * This request does not contain the discussion identifier.
 * The identifier must be supplied by the transport or service layer.
 *
 * @property body Updated text content of the discussion entry.
 */
@Serializable
data class DiscussionUpdateRequest(
    val body: String,
)
