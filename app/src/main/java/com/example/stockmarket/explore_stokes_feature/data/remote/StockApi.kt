package com.example.stockmarket.explore_stokes_feature.data.remote

import com.example.stockmarket.explore_stokes_feature.data.remote.dto.CompanyInfoDto
import com.example.stockmarket.explore_stokes_feature.util.ResponseState
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apikey: String = API_KEY
    ): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): CompanyInfoDto

    companion object {
        const val API_KEY = " RK19NS69ZJ0QQ0CR"
        const val BASE_URL = "https://alphavantage.co"
    }

}