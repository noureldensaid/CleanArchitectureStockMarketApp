package com.example.stockmarket.app_features.show_company_info_feature.presentation.company_info

import com.example.stockmarket.app_features.show_company_info_feature.domain.model.CompanyInfo
import com.example.stockmarket.app_features.show_company_info_feature.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfo: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
