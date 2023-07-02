package com.example.stockmarket.di

import com.example.stockmarket.app_features.show_company_info_feature.data.csv.IntradayInfoParser
import com.example.stockmarket.app_features.show_company_info_feature.data.repository.RepositoryImpl
import com.example.stockmarket.app_features.show_company_info_feature.domain.model.IntradayInfo
import com.example.stockmarket.app_features.show_company_info_feature.domain.repository.Repository
import com.example.stockmarket.core.util.CSVParser
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StockInfoRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStockInfoRepository(
        repositoryImp: RepositoryImpl
    ): Repository

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>


}