package org.kindredhq.discussions.core.validation

import org.kindredhq.discussions.core.domain.model.ReportValidationRules
import org.kindredhq.discussions.core.dto.request.ReportCreateRequest
import org.kindredhq.discussions.core.domain.exceptions.ValidationException
import org.kindredhq.discussions.core.domain.exceptions.FieldError
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ReportValidatorTest {

    private val validReason = "This content is inappropriate."
    private val targetId = Uuid.random()

    @Test
    fun `validateCreate should pass for valid request`() {
        val request = ReportCreateRequest(
            targetId = targetId,
            reason = validReason
        )
        
        ReportValidator.validateCreate(request)
    }

    @Test
    fun `validateCreate should fail for blank reason when required`() {
        val rules = ReportValidationRules(requireReason = true)
        val request = ReportCreateRequest(
            targetId = targetId,
            reason = "  "
        )

        val exception = assertThrows<ValidationException> {
            ReportValidator.validateCreate(request, rules)
        }

        val error = exception.errors.find { it.field == "reason" && it.code == "report.reason.blank" }
        assertEquals("reason", error?.field)
        assertEquals("report.reason.blank", error?.code)
    }

    @Test
    fun `validateCreate should pass for blank reason when not required`() {
        val rules = ReportValidationRules(requireReason = false)
        val request = ReportCreateRequest(
            targetId = targetId,
            reason = ""
        )

        ReportValidator.validateCreate(request, rules)
    }

    @Test
    fun `validateCreate should fail when reason length exceeds limit`() {
        val rules = ReportValidationRules(maxReasonLength = 10)
        val request = ReportCreateRequest(
            targetId = targetId,
            reason = "This reason is way too long"
        )

        val exception = assertThrows<ValidationException> {
            ReportValidator.validateCreate(request, rules)
        }

        val error = exception.errors.find { it.field == "reason" && it.code == "report.reason.tooLong" }
        assertEquals("report.reason.tooLong", error?.code)
        assertTrue(error?.message?.contains("10") == true)
    }

    @Test
    fun `validateCreate should accumulate multiple errors for reason`() {
        // This case might be tricky because both blank and tooLong could trigger if rules allow.
        // But blank is usually short. However, if maxReasonLength is say 0 (not possible by init),
        // or if we have other fields in future.
        // Currently only reason is validated.
        
        val rules = ReportValidationRules(requireReason = true, maxReasonLength = 1)
        val request = ReportCreateRequest(
            targetId = targetId,
            reason = "  " // blank, length 2 > 1
        )

        val exception = assertThrows<ValidationException> {
            ReportValidator.validateCreate(request, rules)
        }

        // reason is blank AND too long
        assertTrue(exception.errors.any { it.code == "report.reason.blank" })
        assertTrue(exception.errors.any { it.code == "report.reason.tooLong" })
    }
}
