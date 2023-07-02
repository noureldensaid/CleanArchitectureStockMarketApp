package com.example.stockmarket.core.util

import java.io.InputStream

interface CSVParser<T> {

    suspend fun parse(input: InputStream): List<T>
}