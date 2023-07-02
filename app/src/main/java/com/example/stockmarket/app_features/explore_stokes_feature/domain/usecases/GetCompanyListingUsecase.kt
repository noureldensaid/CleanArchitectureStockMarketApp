package com.example.stockmarket.app_features.explore_stokes_feature.domain.usecases

import com.example.stockmarket.app_features.explore_stokes_feature.domain.model.CompanyListing
import com.example.stockmarket.app_features.explore_stokes_feature.domain.repository.Repository
import com.example.stockmarket.core.util.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCompanyListingUsecase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<ResponseState<List<CompanyListing>>> {

        return repository.getCompanyListings(fetchFromRemote, query)

    }
}


