package com.example.stockmarket.explore_stokes_feature.domain.repository

import com.example.stockmarket.explore_stokes_feature.domain.model.CompanyListing
import com.example.stockmarket.explore_stokes_feature.util.ResponseState
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<ResponseState<List<CompanyListing>>>


}