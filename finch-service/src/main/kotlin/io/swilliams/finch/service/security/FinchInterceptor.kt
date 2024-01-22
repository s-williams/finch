package io.swilliams.finch.service.security

import io.swilliams.finch.ipapi.IPAPIApi
import io.swilliams.finch.service.dao.RequestRepository
import io.swilliams.finch.service.dao.RequestService
import io.swilliams.finch.service.exceptions.FinchSecurityException
import io.swilliams.finch.service.model.Request
import io.swilliams.finch.service.security.RequestBlockers.isValidCountry
import io.swilliams.finch.service.security.RequestBlockers.isValidIsp
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class FinchInterceptor(
    @Autowired val requestService: RequestService,
    @Autowired val ipApiClient: IPAPIApi
): HandlerInterceptor {

    val verificationFeatureFlag = true

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val requestReceived = OffsetDateTime.now()

        val ipInfo = runBlocking {
            ipApiClient.ipGeolocation()
        }

        request.setAttribute("requestId", UUID.randomUUID())
        request.setAttribute("requestTimestamp", requestReceived)
        request.setAttribute("requestIpAddress", ipInfo.`as`)
        request.setAttribute("requestCountryCode", ipInfo.countryCode)
        request.setAttribute("requestIpProvider", ipInfo.isp)

        val isValidCountry = isValidCountry(ipInfo.country)
        val isValidIsp = isValidIsp(ipInfo.isp)
        if (verificationFeatureFlag && (!isValidCountry || !isValidIsp)) {
            saveRequest(request, "403")

            val messageParts = mutableListOf("Security Exception")
            if (!isValidCountry) {
                messageParts.add("Invalid country: ${ipInfo.country}")
            }
            if (!isValidIsp) {
                messageParts.add("Invalid ISP: ${ipInfo.isp}")
            }
            throw FinchSecurityException(messageParts.joinToString(". ", postfix = "."))
        }
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        saveRequest(request, response.status.toString())
    }

    private fun saveRequest(request: HttpServletRequest, responseCode: String) = requestService.save(Request(
        requestId = request.getAttribute("requestId") as UUID,
        requestUri = request.requestURI,
        requestTimestamp = request.getAttribute("requestTimestamp") as OffsetDateTime,
        httpResponseCode = responseCode,
        requestIpAddress = request.getAttribute("requestIpAddress") as String?,
        requestCountryCode = request.getAttribute("requestCountryCode") as String?,
        requestIpProvider = request.getAttribute("requestIpProvider") as String?,
        timeLapsed = ChronoUnit.MILLIS.between((request.getAttribute("requestTimestamp") as OffsetDateTime), OffsetDateTime.now())
    ))
}
