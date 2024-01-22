package io.swilliams.finch.service.mappers

import io.swilliams.finch.api.model.MinimalEntry
import io.swilliams.finch.service.model.Entry

object ResponseMappers {
    fun createPostEntriesResponse(entries: List<Entry>): List<MinimalEntry> {
        return entries.map {
            MinimalEntry(
                name = it.name,
                transport = it.transport,
                topSpeed = it.topSpeed
            )
        }
    }
}
