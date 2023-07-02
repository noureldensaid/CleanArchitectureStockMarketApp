package com.example.stockmarket.app_features.show_company_info_feature.data.csv

import com.example.stockmarket.app_features.show_company_info_feature.data.mapper.toIntradayInfo
import com.example.stockmarket.app_features.show_company_info_feature.data.remote.dto.IntradayInfoDto
import com.example.stockmarket.app_features.show_company_info_feature.domain.model.IntradayInfo
import com.example.stockmarket.core.util.CSVParser
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntradayInfoParser @Inject constructor() : CSVParser<IntradayInfo> {
    override suspend fun parse(input: InputStream): List<IntradayInfo> {
        val csvReader = CSVReader(InputStreamReader(input))
        return withContext(Dispatchers.IO) {
            csvReader.readAll().drop(1).mapNotNull { line ->
                val timestamp = line.getOrNull(0) ?: return@mapNotNull null
                val close = line.getOrNull(4) ?: return@mapNotNull null
                val dto = IntradayInfoDto(timestamp, close.toDouble())
                dto.toIntradayInfo()
            }.filter {
                it.date.dayOfMonth == LocalDate.now().minusDays(4).dayOfMonth
            }.sortedBy {
                it.date.hour
            }.also {
                csvReader.close()
            }
        }
    }
}