package com.chscorp.cosmeticsstore.presentation.ui

import com.chscorp.cosmeticsstore.domain.product.ProductListItem

data class ProductState(
    val productList: List<ProductListItem>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
