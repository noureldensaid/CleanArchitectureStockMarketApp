package com.example.stockmarket.explore_stokes_feature.domain.usecases

data class Usecases(
    val getCompanyListingUsecase: GetCompanyListingUsecase,
    val getCompanyInfoUsecase: GetCompanyInfoUsecase,
    val getIntradayInfoUsecase: GetIntradayInfoUsecase,
)