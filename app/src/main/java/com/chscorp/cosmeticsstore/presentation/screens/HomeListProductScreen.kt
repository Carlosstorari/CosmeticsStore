package com.chscorp.cosmeticsstore.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chscorp.cosmeticsstore.presentation.PresentationConst.alphabetica
import com.chscorp.cosmeticsstore.presentation.PresentationConst.biggestPrice
import com.chscorp.cosmeticsstore.presentation.PresentationConst.favorite
import com.chscorp.cosmeticsstore.presentation.PresentationConst.invertedAlphabetica
import com.chscorp.cosmeticsstore.presentation.PresentationConst.lowestPrice
import com.chscorp.cosmeticsstore.presentation.PresentationConst.options
import com.chscorp.cosmeticsstore.presentation.PresentationConst.titleFilter
import com.chscorp.cosmeticsstore.presentation.components.CardProductItem
import com.chscorp.cosmeticsstore.presentation.state.FilterBarOption
import com.chscorp.cosmeticsstore.presentation.state.FilterBarState
import com.chscorp.cosmeticsstore.presentation.state.ProductState
import com.chscorp.cosmeticsstore.presentation.theme.DarkCoral
import com.chscorp.cosmeticsstore.presentation.theme.DeepPeach
import com.chscorp.cosmeticsstore.presentation.theme.OldBurgundy
import com.chscorp.cosmeticsstore.presentation.viewModel.MainViewModel

@Composable
fun HomeListProductScreenStateful(
    viewModel: MainViewModel,
    navController: NavController
) {
    val state by viewModel.uiState.collectAsState()
    val stateFilterBar by viewModel.uiFilterBar.collectAsState()
    HomeListProductScreenStateless(
        state = state,
        filterBarState = stateFilterBar,
        navController = navController,
        filterBarOptions =
        FilterBarOption(
            onOptionSelected = { option -> viewModel.selectedFilterOption(option) },
            orderByLowestPrice = {
                viewModel.orderByLowestPrice()
            },
            orderByBiggestPrice = {
                viewModel.orderByBiggestPrice()
            },
            orderByAlphabetica = {
                viewModel.orderByAlphabetica()
            },
            orderByInvertedAlphabetica = {
                viewModel.orderByInvertedAlphabetica()
            },
            orderByFavorites = {
                viewModel.orderByFavorites()
            }
        ),
        { id, isFavorite -> viewModel.updateItemToFavoriteById(id, isFavorite) },
    )
}

@Composable
fun HomeListProductScreenStateless(
    state: ProductState = ProductState(),
    filterBarState: FilterBarState = FilterBarState(),
    navController: NavController,
    filterBarOptions: FilterBarOption,
    onClickItem: (String, Boolean) -> Unit,
) {
    var favoriteStateItem = state.favoriteState
    var selectedOption = filterBarState.selectedOption
    val isLoading = state.isLoading
    var productList = state.productList
    if (!isLoading) {
        Column {
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = titleFilter, Modifier.padding(10.dp))
            Spacer(modifier = Modifier)
            FilterBarState(
                filterBarOptions,
                selectedOption,
            )
            LazyColumn(
                Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (productList != null) {
                    items(productList, key = {product -> product.id}) { product ->
                        CardProductItem(
                            product = product,
                            navController = navController,
                            modifier = Modifier,
                            onFavoriteClicked = { id, isFavorite ->
                                onClickItem(
                                    id,
                                    isFavorite
                                )
                            },
                            favoriteState = favoriteStateItem
                        )
                    }
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
private fun FilterBarState(
    filterBarState: FilterBarOption,
    selectedOption: String
) {
    Row(
        Modifier
            .fillMaxWidth()
            .heightIn(70.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        options.forEach { text ->
            Row(
                modifier = Modifier
                    .padding(all = 8.dp)
            ) {
                Text(
                    text = text,
                    style = typography.bodySmall.merge(),
                    color = OldBurgundy,
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(
                                size = 12.dp,
                            ),
                        )
                        .clickable {
                            filterBarState.onOptionSelected(text)
                        }
                        .background(
                            if (text == selectedOption) {
                                DarkCoral
                            } else {
                                DeepPeach
                            }
                        )
                        .padding(
                            vertical = 12.dp,
                            horizontal = 16.dp,
                        ),
                )
            }
        }
    }
    when (selectedOption) {
        lowestPrice -> {
            filterBarState.orderByLowestPrice()
        }

        biggestPrice -> {
            filterBarState.orderByBiggestPrice()
        }

        alphabetica -> {
            filterBarState.orderByAlphabetica()
        }

        invertedAlphabetica -> {
            filterBarState.orderByInvertedAlphabetica()
        }

        favorite -> {
            filterBarState.orderByFavorites()
        }
    }
}