package io.swilliams.finch.service.controllers

import io.swilliams.finch.api.EntriesApi
import io.swilliams.finch.api.model.PostEntriesResponse
import io.swilliams.finch.service.mappers.ResourceReader
import io.swilliams.finch.service.mappers.ResponseMappers
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class FinchController(): EntriesApi {

    override suspend fun postEntries(body: Resource): ResponseEntity<PostEntriesResponse> {
        val entries = ResourceReader.readBody(body)

        return ResponseEntity(
            PostEntriesResponse(ResponseMappers.createPostEntriesResponse(entries)),
            HttpStatusCode.valueOf(200)
        )
    }
}
