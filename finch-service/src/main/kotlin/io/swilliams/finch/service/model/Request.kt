package io.swilliams.finch.service.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.OffsetDateTime
import java.util.UUID

@Table(name = "requests")
@Entity
class Request(
    @Id
    var requestId: UUID? = null,
    var requestUri: String? = null,
    var requestTimestamp: OffsetDateTime? = null,
    var httpResponseCode: String? = null,
    var requestIpAddress: String? = null,
    var requestCountryCode: String? = null,
    var requestIpProvider: String? = null,
    var timeLapsed: Long? = null,
)
