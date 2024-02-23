package com.chscorp.cosmeticsstore.domain.repository

import com.chscorp.cosmeticsstore.domain.product.ProductListItem
import com.chscorp.cosmeticsstore.domain.util.Resource

interface ProductsRepository {
    suspend fun getProductData() : Resource<List<ProductListItem>>
}