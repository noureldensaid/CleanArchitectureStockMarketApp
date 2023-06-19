package com.example.stockmarket.explore_stokes_feature.domain.repository

import com.example.stockmarket.explore_stokes_feature.domain.model.CompanyInfo
import com.example.stockmarket.explore_stokes_feature.domain.model.CompanyListing
import com.example.stockmarket.explore_stokes_feature.domain.model.IntradayInfo
import com.example.stockmarket.explore_stokes_feature.util.ResponseState
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface Repository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<ResponseState<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): ResponseBody

    suspend fun getCompanyInfo(
        symbol: String
    ): CompanyInfo
}