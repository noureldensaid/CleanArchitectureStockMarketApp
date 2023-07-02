package com.example.stockmarket.app_features.show_company_info_feature.presentation.company_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarket.app_features.show_company_info_feature.domain.usecases.GetCompanyInfoUsecase
import com.example.stockmarket.app_features.show_company_info_feature.domain.usecases.GetIntradayInfoUsecase
import com.example.stockmarket.core.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCompanyInfoUsecase: GetCompanyInfoUsecase,
    private val getIntradayInfoUsecase: GetIntradayInfoUsecase
) : ViewModel() {

    var companyInfoState by mutableStateOf(CompanyInfoState())

    init {
        getCompanyInfo(savedStateHandle)
    }

    private fun getCompanyInfo(savedStateHandle: SavedStateHandle) {
        viewModelScope.launch {
            val symbol = savedStateHandle.get<String>("symbol") ?: return@launch
            companyInfoState = companyInfoState.copy(isLoading = true)
            val companyInfoResult = async { getCompanyInfoUsecase(symbol) }
            val intradayInfoResult = async { getIntradayInfoUsecase(symbol) }
            when (val result = companyInfoResult.await()) {
                is ResponseState.Error -> {
                    companyInfoState = companyInfoState.copy(
                        error = result.message,
                        isLoading = false
                    )
                }

                is ResponseState.Loading -> {
                    companyInfoState = companyInfoState.copy(
                        isLoading = true
                    )
                }

                is ResponseState.Success -> {
                    companyInfoState = companyInfoState.copy(
                        company = result.data,
                        isLoading = false
                    )
                }
            }
            when (val result = intradayInfoResult.await()) {
                is ResponseState.Error -> {
                    companyInfoState = companyInfoState.copy(
                        error = result.message,
                        isLoading = false
                    )
                }

                is ResponseState.Loading -> {
                    companyInfoState = companyInfoState.copy(
                        isLoading = true
                    )
                }

                is ResponseState.Success -> {
                    companyInfoState = companyInfoState.copy(
                        stockInfo = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
            }
        }
    }


}