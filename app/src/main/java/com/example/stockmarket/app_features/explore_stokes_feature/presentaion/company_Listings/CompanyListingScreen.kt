package com.example.stockmarket.app_features.explore_stokes_feature.presentaion.company_Listings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stockmarket.app_features.destinations.CompanyInfoScreenDestination
import com.example.stockmarket.app_features.explore_stokes_feature.presentaion.company_Listings.components.CompanyListingItem
import com.example.stockmarket.app_features.explore_stokes_feature.presentaion.company_Listings.components.StockSearchBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun CompanyListingScreen(
    viewModel: CompanyListingViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val swiperRefresh = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )

    val focusRequester = remember { FocusRequester() }


    val state = viewModel.state

    Column(modifier = Modifier.fillMaxSize()) {
        StockSearchBar(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = state.searchQuery,
            onValueChange = { viewModel.onEvent(CompanyListingEvent.OnSearchQueryChange(it)) },
        )

        SwipeRefresh(
            state = swiperRefresh,
            onRefresh = { viewModel.onEvent(CompanyListingEvent.Refresh) }
        ) {
            LazyColumn {
                itemsIndexed(state.companies) { index, item ->
                    // val company = state.companies[index]
                    CompanyListingItem(
                        companyItem = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                navigator.navigate(CompanyInfoScreenDestination(item.symbol))
                            }
                    )

                    if (index < state.companies.size) {
                        Divider(modifier = Modifier.padding(8.dp))
                    }

                }
            }
        }
    }


}