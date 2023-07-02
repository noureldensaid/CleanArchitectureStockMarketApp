package com.example.stockmarket.app_features.show_company_info_feature.domain.model

data class CompanyInfo(
    val name: String,
    val symbol: String,
    val description: String,
    val country: String,
    val industry: String,
)
