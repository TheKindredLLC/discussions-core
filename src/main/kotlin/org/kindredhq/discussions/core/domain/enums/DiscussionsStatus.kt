package org.kindredhq.discussions.core.domain.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the moderation and logical visibility state of a [Discussion].
 *
 * This enum models structural state only. It does not enforce
 * retention rules, purge timing, or moderation workflow.
 *
 * Decisions regarding soft deletion, hard deletion, or
 * state transitions are the responsibility of the implementing service.
 */
@Serializable
public enum class DiscussionsStatus {
    /**
     * The discussion is fully visible and accessible.
     */
    @SerialName("visible")
    VISIBLE,

    /**
     * The discussion has been automatically hidden due to reports
     * reaching a configured threshold.
     */
    @SerialName("hidden_by_reports")
    HIDDEN_BY_REPORTS,

    /**
     * The discussion has been manually hidden by a moderator.
     */
    @SerialName("hidden_by_moderator")
    HIDDEN_BY_MODERATOR,

    /**
     * The discussion was deleted by its original author.
     */
    @SerialName("deleted_by_author")
    DELETED_BY_AUTHOR,

    /**
     * The discussion was deleted by a moderator.
     */
    @SerialName("deleted_by_moderator")
    DELETED_BY_MODERATOR,
}
