package io.swilliams.finch.service.mappers

import io.swilliams.finch.api.model.MinimalEntry
import io.swilliams.finch.service.model.Entry
import io.swilliams.finch.service.model.Request
import java.util.*

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

    fun Entry.toRequest() = Request(
        requestId = UUID.randomUUID(),
        requestUri = this.id,
        requestTimestamp = null,
        httpResponseCode = null,
        requestIpAddress = null,
        requestCountryCode = null,
        requestIpProvider = null,
        timeLapsed = null,
    )
}