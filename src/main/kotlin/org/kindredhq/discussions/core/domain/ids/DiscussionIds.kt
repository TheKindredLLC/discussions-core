package org.kindredhq.discussions.core.domain.ids

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
object DiscussionIds {
    fun newId(): Uuid = Uuid.random()
}
