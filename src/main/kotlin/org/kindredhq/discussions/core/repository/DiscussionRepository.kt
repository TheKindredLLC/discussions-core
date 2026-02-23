package org.kindredhq.discussions.core.repository

import org.kindredhq.discussions.core.domain.enums.DiscussionsStatus
import org.kindredhq.discussions.core.domain.model.Discussion
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Defines the persistence contract for [Discussion] entities.
 * Implementations may perform optimistic locking or concurrency checks.
 *
 * This interface describes storage and retrieval operations only.
 * It does not define authorization rules, moderation policy,
 * visibility filtering strategy, pagination strategy, or
 * infrastructure details.
 *
 * Implementations are responsible for:
 * - Storage technology (SQL, NoSQL, in-memory, etc.)
 * - Indexing strategy
 * - Pagination mechanics
 * - Transaction management
 * - Concurrency handling
 *
 * Callers are responsible for:
 * - Authorization checks
 * - Moderation enforcement
 * - Visibility filtering logic
 * - Validation prior to persistence
 */
@OptIn(ExperimentalUuidApi::class)
public interface DiscussionRepository {
    /* =======================
       Creation
       ======================= */

    /**
     * Persists a new [Discussion].
     *
     * @param discussion The discussion to store.
     * @return The persisted discussion instance.
     */
    suspend fun insert(discussion: Discussion): Discussion

    /* =======================
       Reads
       ======================= */

    /**
     * Retrieves a discussion by its identifier.
     *
     * @param id The discussion identifier.
     * @return The discussion if found, or `null` otherwise.
     */
    suspend fun findById(id: Uuid): Discussion?

    /**
     * Retrieves root-level discussions for a given target.
     *
     * A root discussion is defined as one whose `parentId` is `null`.
     *
     * @param targetId Optional external target identifier.
     * @param statuses The set of allowed [DiscussionsStatus] values.
     * @param limit Maximum number of results to return.
     *
     * The ordering and pagination mechanism are implementation-defined.
     */
    suspend fun listRootDiscussions(
        targetId: Uuid?,
        statuses: Set<DiscussionsStatus>,
        limit: Int,
    ): List<Discussion>

    /**
     * Retrieves replies for a specific parent discussion.
     *
     * @param parentId The parent discussion identifier.
     * @param statuses The set of allowed [DiscussionsStatus] values.
     * @param limit Maximum number of results to return.
     *
     * The ordering and pagination mechanism are implementation-defined.
     */
    suspend fun listReplies(
        parentId: Uuid,
        statuses: Set<DiscussionsStatus>,
        limit: Int,
    ): List<Discussion>

    /**
     * Counts direct child discussions for each parent identifier.
     *
     * @param parentIds A set of parent discussion identifiers.
     * @return A mapping of parentId to number of direct children.
     */
    suspend fun countChildren(parentIds: Set<Uuid>): Map<Uuid, Int>

    /* =======================
       Mutation
       ======================= */

    /**
     * Updates an existing discussion.
     *
     * @param discussion The updated discussion entity.
     * @return The updated discussion if it exists, or `null` if not found.
     */
    suspend fun update(discussion: Discussion): Discussion?

    /**
     * Deletes a discussion by identifier.
     *
     * The semantics of deletion (soft delete, hard delete, retention rules)
     * are implementation-defined.
     *
     * @param id The discussion identifier.
     * @return `true` if a record was affected, otherwise `false`.
     */
    suspend fun delete(id: Uuid): Boolean
}
