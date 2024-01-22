package io.swilliams.finch.service.model

import java.util.UUID

data class Entry (
    val uuid: UUID,
    val id: String,
    val name: String,
    val likes: String,
    val transport: String,
    val avgSpeed: Float,
    val topSpeed: Float,
)
