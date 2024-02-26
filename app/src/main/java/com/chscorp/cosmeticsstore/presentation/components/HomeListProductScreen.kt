package com.chscorp.cosmeticsstore.presentation.components

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.chscorp.cosmeticsstore.presentation.states.ProductState
import com.chscorp.cosmeticsstore.presentation.ui.theme.DarkCoral
import com.chscorp.cosmeticsstore.presentation.ui.theme.DeepPeach
import com.chscorp.cosmeticsstore.presentation.ui.theme.OldBurgundy
import com.chscorp.cosmeticsstore.presentation.ui.viewModel.MainViewModel

@Composable
fun HomeListProductScreenStateful(
    viewModel: MainViewModel
) {
    val state by viewModel.uiState.collectAsState()
    HomeListProductScreenStateless(state = state)
}

@Composable
fun HomeListProductScreenStateless(
    state: ProductState = ProductState()
) {
    val isLoading = state.isLoading
    if (!isLoading) {
        Column {
            var productList = state.productList
            val options = listOf(
                "Menor preço",
                "Maior preço",
                "A-Z",
                "Z-A",
                "Favoritos"
            )
            var selectedOption by remember {
                mutableStateOf("")
            }
            val onSelectionChange = { text: String ->
                selectedOption = text
            }
            Text(text = "Ordenar por")
            Spacer(modifier = Modifier)
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
                                    onSelectionChange(text)
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
                "Menor preço" -> {
                    productList = productList?.sortedBy { it.price?.toFloat() }
                }

                "Maior preço" -> {
                    productList = productList?.sortedByDescending { it.price?.toFloat() }
                }

                "A-Z" -> {
                    productList = productList?.sortedBy { it.brand }
                }

                "Z-A" -> {
                    productList = productList?.sortedByDescending { it.brand }
                }

                else -> {}
            }
            LazyColumn(
                Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (productList != null) {
                    for (product in productList) {
                        item {
                            CardProductItem(product)
                        }
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