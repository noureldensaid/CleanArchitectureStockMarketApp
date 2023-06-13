package com.example.stockmarket.explore_stokes_feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CompanyListingEntity::class],
    version = 1
)
abstract class StockDatabase : RoomDatabase() {
    abstract val dao: StockDao

    companion object {
        const val DATABASE_NAME = "stock_db"
    }
}