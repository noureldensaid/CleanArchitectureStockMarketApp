package com.example.stockmarket.app_features.explore_stokes_feature.data.mapper

import com.example.stockmarket.app_features.explore_stokes_feature.data.local.CompanyListingEntity
import com.example.stockmarket.app_features.explore_stokes_feature.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

