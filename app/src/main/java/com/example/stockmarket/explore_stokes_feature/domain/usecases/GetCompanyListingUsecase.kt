package com.example.stockmarket.explore_stokes_feature.domain.usecases

import com.example.stockmarket.explore_stokes_feature.domain.model.CompanyListing
import com.example.stockmarket.explore_stokes_feature.domain.repository.Repository
import com.example.stockmarket.explore_stokes_feature.util.ResponseState
import kotlinx.coroutines.flow.Flow

class GetCompanyListingUsecase(
    private val repository: Repository
) {
    suspend operator fun invoke(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<ResponseState<List<CompanyListing>>> {

        return repository.getCompanyListings(fetchFromRemote, query)
        
    }
}


