package com.example.stockmarket.app_features.show_company_info_feature.data.mapper

import com.example.stockmarket.app_features.show_company_info_feature.data.remote.dto.CompanyInfoDto
import com.example.stockmarket.app_features.show_company_info_feature.data.remote.dto.IntradayInfoDto
import com.example.stockmarket.app_features.show_company_info_feature.domain.model.CompanyInfo
import com.example.stockmarket.app_features.show_company_info_feature.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

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