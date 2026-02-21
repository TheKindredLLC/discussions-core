package org.kindredhq.discussions.core.domain.enums

import kotlinx.serialization.Serializable

/**
 * Represents the moderation and visibility state of a [Discussion].
 *
 * This enum describes logical visibility states only.
 * It does not define or enforce persistence behavior.
 *
 * The handling of soft deletion, hard deletion, retention windows,
 * or purge policies is the responsibility of the implementing service.
 */
@Serializable
enum class DiscussionsStatus {

    /**
     * The discussion is fully visible and accessible.
     */
    VISIBLE,

    /**
     * The discussion has been automatically hidden due to reports
     * reaching a configured threshold.
     */
    HIDDEN_BY_REPORTS,

    /**
     * The discussion has been manually hidden by a moderator.
     */
    HIDDEN_BY_MODERATOR,

    /**
     * The discussion was deleted by its original author.
     */
    DELETED_BY_AUTHOR,

    /**
     * The discussion was deleted by a moderator.
     */
    DELETED_BY_MODERATOR,
}
