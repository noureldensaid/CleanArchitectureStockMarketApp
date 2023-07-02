package com.example.stockmarket.app_features.show_company_info_feature.domain.repository

import com.example.stockmarket.app_features.show_company_info_feature.domain.model.CompanyInfo
import okhttp3.ResponseBody

interface Repository {

    suspend fun getIntradayInfo(
        symbol: String
    ): ResponseBody

    suspend fun getCompanyInfo(
        symbol: String
    ): CompanyInfo
}