package com.example.stockmarket.app_features.explore_stokes_feature.presentaion.company_Listings

sealed class CompanyListingEvent {
    object Refresh : CompanyListingEvent()
    data class OnSearchQueryChange(val query: String) : CompanyListingEvent()
}