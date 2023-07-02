package com.example.stockmarket.app_features.explore_stokes_feature.data.csv

import com.example.stockmarket.app_features.explore_stokes_feature.domain.model.CompanyListing
import com.example.stockmarket.core.util.CSVParser
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompanyListingsParser @Inject constructor() : CSVParser<CompanyListing> {

    override suspend fun parse(input: InputStream): List<CompanyListing> {

        val csvReader = CSVReader(InputStreamReader(input))

        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val symbol = line.getOrNull(0)
                    val name = line.getOrNull(1)
                    val exchange = line.getOrNull(2)

                    CompanyListing(
                        name = name ?: return@mapNotNull null,
                        symbol = symbol ?: return@mapNotNull null,
                        exchange = exchange ?: return@mapNotNull null,
                    )
                }
                .also { csvReader.close() }
        }
    }
}