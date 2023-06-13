package com.example.stockmarket.explore_stokes_feature.data.csv

import java.io.InputStream

interface CSVParser<T> {

    suspend fun parse(input: InputStream): List<T>
}