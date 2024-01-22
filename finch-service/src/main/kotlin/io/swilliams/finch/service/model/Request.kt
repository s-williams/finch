package io.swilliams.finch.service.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.OffsetDateTime
import java.util.UUID

@Table(name = "requests")
@Entity
data class Request(
    @Id
    val requestId: UUID,
    val requestUri: String,
    val requestTimestamp: OffsetDateTime?,
    val httpResponseCode: String?,
    val requestIpAddress: String?,
    val requestCountryCode: String?,
    val requestIpProvider: String?,
    val timeLapsed: Long?,
)