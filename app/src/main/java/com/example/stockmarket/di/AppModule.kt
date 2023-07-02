package com.example.stockmarket.di

import android.app.Application
import androidx.room.Room
import com.example.stockmarket.app_features.explore_stokes_feature.data.local.StockDatabase
import com.example.stockmarket.app_features.explore_stokes_feature.data.remote.StockApi
import com.example.stockmarket.app_features.show_company_info_feature.data.remote.StockInfoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideStockInfoApi(): StockInfoApi {
        return Retrofit.Builder()
            .baseUrl(StockInfoApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): StockDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = StockDatabase::class.java,
            name = StockDatabase.DATABASE_NAME
        ).build()
    }

}


