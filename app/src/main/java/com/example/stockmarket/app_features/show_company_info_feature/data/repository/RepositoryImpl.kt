package com.example.stockmarket.app_features.show_company_info_feature.data.repository

import com.example.stockmarket.app_features.explore_stokes_feature.domain.model.CompanyListing
import com.example.stockmarket.app_features.show_company_info_feature.data.mapper.toCompanyInfo
import com.example.stockmarket.app_features.show_company_info_feature.data.remote.StockInfoApi
import com.example.stockmarket.app_features.show_company_info_feature.domain.model.CompanyInfo
import com.example.stockmarket.app_features.show_company_info_feature.domain.repository.Repository
import com.example.stockmarket.core.util.CSVParser
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val api: StockInfoApi,
    private val csvParser: CSVParser<CompanyListing>
) : Repository {


    override suspend fun getIntradayInfo(symbol: String): ResponseBody {
        return api.getIntradayInfo(symbol)
    }

    override suspend fun getCompanyInfo(symbol: String): CompanyInfo {
        return api.getCompanyInfo(symbol).toCompanyInfo()
    }
}