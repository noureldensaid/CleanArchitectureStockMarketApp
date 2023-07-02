package com.example.stockmarket.app_features.show_company_info_feature.domain.usecases

import com.example.stockmarket.app_features.show_company_info_feature.domain.model.CompanyInfo
import com.example.stockmarket.app_features.show_company_info_feature.domain.repository.Repository
import com.example.stockmarket.core.util.ResponseState
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCompanyInfoUsecase @Inject constructor(
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