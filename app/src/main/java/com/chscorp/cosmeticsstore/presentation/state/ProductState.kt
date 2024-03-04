package com.chscorp.cosmeticsstore.presentation.state

import com.chscorp.cosmeticsstore.domain.product.ProductInfo
import com.chscorp.cosmeticsstore.domain.product.ProductListItem

data class ProductState(
    val productList: List<ProductListItem>? = null,
    val productInfoList: List<ProductInfo>? = null,
    val favoriteState: MutableMap<String, Boolean> = mutableMapOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)
