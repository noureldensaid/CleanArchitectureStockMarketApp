package com.example.stockmarket.app_features.show_company_info_feature.domain.model

import java.time.LocalDateTime

data class IntradayInfo(
    val date: LocalDateTime,
    val close: Double
)