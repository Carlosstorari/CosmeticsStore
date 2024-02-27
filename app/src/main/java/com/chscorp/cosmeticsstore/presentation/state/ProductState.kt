package com.chscorp.cosmeticsstore.presentation.state

import com.chscorp.cosmeticsstore.domain.product.ProductListItem

data class ProductState(
    val productList: List<ProductListItem>? = null,
    val selectedOption: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
