package com.chscorp.cosmeticsstore.data.mappers

import com.chscorp.cosmeticsstore.data.remote.ProductDto
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