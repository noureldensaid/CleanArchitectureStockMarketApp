package com.example.stockmarket.app_features.explore_stokes_feature.presentaion.company_Listings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarket.app_features.explore_stokes_feature.domain.model.CompanyListing

@Composable
fun CompanyListingItem(
    modifier: Modifier = Modifier,
    companyItem: CompanyListing,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            // first Row
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = companyItem.name,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = companyItem.exchange,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            // second Row
            Text(companyItem.symbol)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewCompanyListingItem() {
    CompanyListingItem(
        modifier = Modifier.fillMaxSize(),
        companyItem = CompanyListing(
            name = "Marissa English",
            symbol = "dolor",
            exchange = "ultricies"
        )
    )
}