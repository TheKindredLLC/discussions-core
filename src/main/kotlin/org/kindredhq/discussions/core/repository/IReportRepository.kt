package org.kindredhq.discussions.core.repository

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import org.kindredhq.discussions.core.domain.enums.ReportStatus
import org.kindredhq.discussions.core.domain.model.Report
import org.kindredhq.discussions.core.domain.model.ReportQuery

/**
 * Defines the persistence contract for [Report] entities.
 *
 * This interface describes storage and retrieval operations only.
 * It does not define moderation workflows, authorization rules,
 * dashboard policies, or retention strategies.
 *
 * Implementations are responsible for:
 * - Storage technology
 * - Indexing strategy
 * - Pagination mechanics
 * - Transaction management
 */
@OptIn(ExperimentalUuidApi::class)
interface ReportRepository {

    /* =======================
       Creation
       ======================= */

    /**
     * Persists a new [Report].
     *
     * @param report The report to store.
     * @return The persisted report.
     */
    suspend fun insert(report: Report): Report

    /* =======================
       Reads
       ======================= */

    /**
     * Retrieves a report by its identifier.
     *
     * @param id The report identifier.
     * @return The report if found, or `null` otherwise.
     */
    suspend fun findById(id: Uuid): Report?

    /**
     * Retrieves reports matching the provided [ReportQuery].
     *
     * All filtering behavior is defined by the properties of [ReportQuery].
     * Any `null` property in the query indicates that the corresponding
     * filter must not be applied.
     *
     * Implementations are responsible for:
     * - Applying filters correctly
     * - Defining ordering semantics
     * - Enforcing pagination behavior
     * - Handling performance considerations
     *
     * This method does not perform authorization checks.
     * Access control and moderation policy enforcement are the responsibility
     * of the calling service layer.
     *
     * @param query Filter criteria for retrieving reports.
     * @return A list of reports matching the query. If no reports match,
     *         an empty list must be returned.
     */
    suspend fun list(que: ReportQuery): List<Report>

    /* =======================
       Mutation
       ======================= */

    /**
     * Updates the lifecycle status of a report.
     *
     * @param id Report identifier.
     * @param status New status.
     * @return `true` if a record was affected, otherwise `false`.
     */
    suspend fun updateStatus(
        id: Uuid,
        status: ReportStatus,
    ): Boolean
}
