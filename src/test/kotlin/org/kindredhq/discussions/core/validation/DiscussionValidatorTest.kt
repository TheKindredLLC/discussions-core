package org.kindredhq.discussions.core.validation

import org.kindredhq.discussions.core.domain.model.DiscussionValidationRules
import org.kindredhq.discussions.core.dto.common.DiscussionUpdateRequest
import org.kindredhq.discussions.core.dto.request.DiscussionCreateRequest
import org.kindredhq.discussions.core.domain.exceptions.ValidationException
import org.kindredhq.discussions.core.domain.exceptions.FieldError
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class DiscussionValidatorTest {

    private val validUserId = "user-123"
    private val validBody = "This is a valid discussion body."
    private val targetId = Uuid.random()

    @Test
    fun `validateCreate should pass for valid request`() {
        val request = DiscussionCreateRequest(
            targetId = targetId,
            userId = validUserId,
            body = validBody
        )
        
        DiscussionValidator.validateCreate(request)
    }

    @Test
    fun `validateCreate should fail for blank user id`() {
        val request = DiscussionCreateRequest(
            userId = "  ",
            body = validBody
        )

        val exception = assertThrows<ValidationException> {
            DiscussionValidator.validateCreate(request)
        }

        val error = exception.errors.find { it.field == "user_id" }
        assertEquals("discussion.userId.blank", error?.code)
    }

    @Test
    fun `validateCreate should fail for blank body`() {
        val request = DiscussionCreateRequest(
            userId = validUserId,
            body = ""
        )

        val exception = assertThrows<ValidationException> {
            DiscussionValidator.validateCreate(request)
        }

        val error = exception.errors.find { it.field == "body" }
        assertEquals("discussion.body.blank", error?.code)
    }

    @Test
    fun `validateCreate should fail when body length exceeds limit`() {
        val rules = DiscussionValidationRules(maxBodyLength = 10)
        val request = DiscussionCreateRequest(
            userId = validUserId,
            body = "This body is too long"
        )

        val exception = assertThrows<ValidationException> {
            DiscussionValidator.validateCreate(request, rules)
        }

        val error = exception.errors.find { it.field == "body" }
        assertEquals("discussion.body.tooLong", error?.code)
        assertTrue(error?.message?.contains("10") == true)
    }

    @Test
    fun `validateCreate should fail when language is required but missing`() {
        val rules = DiscussionValidationRules(requireLanguage = true)
        val request = DiscussionCreateRequest(
            userId = validUserId,
            body = validBody,
            language = null
        )

        val exception = assertThrows<ValidationException> {
            DiscussionValidator.validateCreate(request, rules)
        }

        val error = exception.errors.find { it.field == "language" }
        assertEquals("discussion.language.required", error?.code)
    }

    @Test
    fun `validateCreate should pass when language is required and provided`() {
        val rules = DiscussionValidationRules(requireLanguage = true)
        val request = DiscussionCreateRequest(
            userId = validUserId,
            body = validBody,
            language = "en-US"
        )

        DiscussionValidator.validateCreate(request, rules)
    }

    @Test
    fun `validateCreate should accumulate multiple errors`() {
        val rules = DiscussionValidationRules(maxBodyLength = 5, requireLanguage = true)
        val request = DiscussionCreateRequest(
            userId = "",
            body = "Too long",
            language = null
        )

        val exception = assertThrows<ValidationException> {
            DiscussionValidator.validateCreate(request, rules)
        }

        assertEquals(3, exception.errors.size)
        assertTrue(exception.errors.any { it.field == "user_id" })
        assertTrue(exception.errors.any { it.field == "body" })
        assertTrue(exception.errors.any { it.field == "language" })
    }

    @Test
    fun `validateUpdate should pass for valid request`() {
        val request = DiscussionUpdateRequest(body = validBody)
        DiscussionValidator.validateUpdate(request)
    }

    @Test
    fun `validateUpdate should fail for blank body`() {
        val request = DiscussionUpdateRequest(body = "   ")

        val exception = assertThrows<ValidationException> {
            DiscussionValidator.validateUpdate(request)
        }

        val error = exception.errors.find { it.field == "body" }
        assertEquals("discussion.body.blank", error?.code)
    }

    @Test
    fun `validateUpdate should fail when body exceeds limit`() {
        val rules = DiscussionValidationRules(maxBodyLength = 5)
        val request = DiscussionUpdateRequest(body = "Too long")

        val exception = assertThrows<ValidationException> {
            DiscussionValidator.validateUpdate(request, rules)
        }

        val error = exception.errors.find { it.field == "body" }
        assertEquals("discussion.body.tooLong", error?.code)
    }
}
