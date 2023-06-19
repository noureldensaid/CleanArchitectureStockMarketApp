package com.example.stockmarket.explore_stokes_feature.data.mapper

import com.example.stockmarket.explore_stokes_feature.data.local.CompanyListingEntity
import com.example.stockmarket.explore_stokes_feature.data.remote.dto.CompanyInfoDto
import com.example.stockmarket.explore_stokes_feature.data.remote.dto.IntradayInfoDto
import com.example.stockmarket.explore_stokes_feature.domain.model.CompanyInfo
import com.example.stockmarket.explore_stokes_feature.domain.model.CompanyListing
import com.example.stockmarket.explore_stokes_feature.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}

fun IntradayInfoDto.toIntradayInfo(): IntradayInfo {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return IntradayInfo(
        date = localDateTime,
        close = close
    )
}