package com.example.stockmarket.app_features.explore_stokes_feature.domain.repository

import com.example.stockmarket.app_features.explore_stokes_feature.domain.model.CompanyListing
import com.example.stockmarket.core.util.ResponseState
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<ResponseState<List<CompanyListing>>>


}