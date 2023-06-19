package com.example.stockmarket.explore_stokes_feature.domain.usecases

import com.example.stockmarket.explore_stokes_feature.data.csv.IntradayInfoParser
import com.example.stockmarket.explore_stokes_feature.domain.model.IntradayInfo
import com.example.stockmarket.explore_stokes_feature.domain.repository.Repository
import com.example.stockmarket.explore_stokes_feature.util.ResponseState
import retrofit2.HttpException
import java.io.IOException

class GetIntradayInfoUsecase(
    private val repository: Repository,
    private val intradayInfoParser: IntradayInfoParser
) {
    suspend operator fun invoke(symbol: String): ResponseState<List<IntradayInfo>> {
        return try {
            val response = repository.getIntradayInfo(symbol)
            val results = intradayInfoParser.parse(response.byteStream())
            ResponseState.Success(results)
        } catch (e: IOException) {
            e.printStackTrace()
            ResponseState.Error(
                message = "Couldn't load intraday info"
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            ResponseState.Error(
                message = "Couldn't load intraday info"
            )
        }
    }
}