package com.example.stockmarket.explore_stokes_feature.domain.usecases

import com.example.stockmarket.explore_stokes_feature.domain.model.CompanyInfo
import com.example.stockmarket.explore_stokes_feature.domain.repository.Repository
import com.example.stockmarket.explore_stokes_feature.util.ResponseState
import retrofit2.HttpException
import java.io.IOException

class GetCompanyInfoUsecase(
    private val repository: Repository
) {
    suspend operator fun invoke(
        symbol: String
    ): ResponseState<CompanyInfo> {
        return try {
            val result = repository.getCompanyInfo(symbol)
            ResponseState.Success(result)
        } catch (e: IOException) {
            e.printStackTrace()
            ResponseState.Error(
                message = "Couldn't load company info"
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            ResponseState.Error(
                message = "Couldn't load company info"
            )
        }
    }
}