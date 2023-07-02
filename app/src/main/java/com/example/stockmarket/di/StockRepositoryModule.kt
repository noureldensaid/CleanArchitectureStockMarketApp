package com.example.stockmarket.di

import com.example.stockmarket.app_features.explore_stokes_feature.data.csv.CompanyListingsParser
import com.example.stockmarket.app_features.explore_stokes_feature.data.repository.RepositoryImpl
import com.example.stockmarket.app_features.explore_stokes_feature.domain.model.CompanyListing
import com.example.stockmarket.app_features.explore_stokes_feature.domain.repository.Repository
import com.example.stockmarket.core.util.CSVParser
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StockRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        repositoryImp: RepositoryImpl
    ): Repository

    @Binds
    @Singleton
    abstract fun bindCompanyListingParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListing>


}