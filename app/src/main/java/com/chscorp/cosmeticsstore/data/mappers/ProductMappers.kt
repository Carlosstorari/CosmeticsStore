package com.chscorp.cosmeticsstore.data.mappers

import com.chscorp.cosmeticsstore.data.remote.ProductDto
import com.chscorp.cosmeticsstore.domain.product.ProductInfo
import com.chscorp.cosmeticsstore.domain.product.ProductListItem

fun List<ProductDto>.toListProductItem(): List<ProductListItem> {
     return this.mapNotNull {
         ProductListItem(
             id = it.id,
             brand = it.brand,
             price = it.price,
             description = it.description
         )
     }.toList()
}

fun List<ProductDto>.toListProductInfo(): List<ProductInfo> {
    return this.mapNotNull {
        ProductInfo(
            id = it.id,
            brand = it.brand,
            price = it.price,
            category = it.category,
            type = it.type,
            rating = it.rating
        )
    }.toList()
}

