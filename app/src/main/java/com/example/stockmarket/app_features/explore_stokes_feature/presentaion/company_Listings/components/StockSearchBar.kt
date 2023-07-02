package com.example.stockmarket.app_features.explore_stokes_feature.presentaion.company_Listings.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun StockSearchBar(
    modifier: Modifier,
    value: String,
    onValueChange: (newQuery: String) -> Unit,
    placeholder: String = "Search...",
    iconColor: Color = MaterialTheme.colorScheme.onBackground,
    maxLines: Int = 1
) {
    OutlinedTextField(
        modifier = modifier,
        placeholder = { Text(placeholder) },
        value = value,
        onValueChange = { onValueChange(it) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "",
                tint = iconColor
            )
        },
        maxLines = maxLines
    )
}


