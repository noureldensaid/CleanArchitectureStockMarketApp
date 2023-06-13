package com.example.stockmarket.di

import com.example.stockmarket.explore_stokes_feature.data.csv.CSVParser
import com.example.stockmarket.explore_stokes_feature.data.csv.CompanyListingsParser
import com.example.stockmarket.explore_stokes_feature.data.repository.RepositoryImpl
import com.example.stockmarket.explore_stokes_feature.domain.model.CompanyListing
import com.example.stockmarket.explore_stokes_feature.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

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