package com.example.stockmarket.app_features.explore_stokes_feature.presentaion.company_Listings

import com.example.stockmarket.app_features.explore_stokes_feature.domain.model.CompanyListing

data class CompanyListingState(
    val companies: List<CompanyListing> = emptyList(),
    val searchQuery: String = "",
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
)
