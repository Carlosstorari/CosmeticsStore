package com.chscorp.cosmeticsstore.data.mappers

import com.chscorp.cosmeticsstore.data.remote.ProductsListDto
import com.chscorp.cosmeticsstore.domain.product.ProductListItem

fun ProductsListDto.toListProductItem(): List<ProductListItem> {
     return data.mapNotNull {
         ProductListItem(
             id = it.id,
             brand = it.brand,
             price = it.price,
             description = it.description
         )
     }.toList()
}