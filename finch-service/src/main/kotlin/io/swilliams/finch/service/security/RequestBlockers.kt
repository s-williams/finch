package io.swilliams.finch.service.security

import io.swilliams.finch.ipapi.model.IpGeolocation200Response

object RequestBlockers {

    private val invalidCountries = setOf(
        "China",
        "Spain",
        "USA"
    )

    private val invalidISPs = setOf(
        "AWS",
        "GCP",
        "Azure"
    )

    fun isValidCountry(country: String?) = country?.let { !invalidCountries.any { it.lowercase() in country.lowercase() } } ?: true
    fun isValidIsp(isp: String?) = isp?.let { !invalidISPs.any { it.lowercase() in isp.lowercase() } } ?: true
}
