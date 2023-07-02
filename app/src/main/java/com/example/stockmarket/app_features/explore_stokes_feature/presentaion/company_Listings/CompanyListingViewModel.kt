package com.example.stockmarket.app_features.explore_stokes_feature.presentaion.company_Listings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarket.app_features.explore_stokes_feature.domain.usecases.GetCompanyListingUsecase
import com.example.stockmarket.core.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class CompanyListingViewModel @Inject constructor(
    private val getCompanyListingUsecase: GetCompanyListingUsecase
) : ViewModel() {

    var state by mutableStateOf(CompanyListingState())

    private var searchJob: Job? = null

    init {
        getCompanyListings()
    }

    fun onEvent(event: CompanyListingEvent) {
        when (event) {

            is CompanyListingEvent.Refresh -> {
                getCompanyListings(fetchFromRemote = true)
            }

            is CompanyListingEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(0.5.seconds)
                    getCompanyListings()
                }
            }

        }
    }

    private fun getCompanyListings(
        query: String = state.searchQuery.lowercase(), fetchFromRemote: Boolean = false
    ) {
        val getCompanyListingUsecase = getCompanyListingUsecase
        viewModelScope.launch {
            getCompanyListingUsecase(fetchFromRemote, query).collect { result ->
                when (result) {
                    is ResponseState.Error -> {
                        TODO()
                    }

                    is ResponseState.Loading -> {
                        state = state.copy(
                            isLoading = result.isLoading
                        )
                    }

                    is ResponseState.Success -> {
                        result.data?.let { listings ->
                            state = state.copy(
                                companies = listings
                            )
                        }
                    }
                }
            }
        }
    }


}