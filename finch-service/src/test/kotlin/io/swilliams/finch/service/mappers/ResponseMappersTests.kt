package io.swilliams.finch.service.mappers

import io.swilliams.finch.api.model.MinimalEntry
import io.swilliams.finch.service.model.Entry
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class ResponseMappersTest {

    @Test
    fun testCreatePostEntriesResponse() {
        val entries = listOf(
            Entry(
                uuid = UUID.randomUUID(),
                id = "id",
                name = "name",
                likes = "likes",
                transport = "transport",
                avgSpeed = 1.0f,
                topSpeed = 5.0f,
            )
        )
        assertEquals(
            listOf(MinimalEntry(
                name = "name",
                transport = "transport",
                topSpeed = 5.0f,
            )),
            ResponseMappers.createPostEntriesResponse(entries)
        )
    }
}