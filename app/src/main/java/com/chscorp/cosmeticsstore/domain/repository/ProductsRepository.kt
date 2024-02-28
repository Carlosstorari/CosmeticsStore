package com.chscorp.cosmeticsstore.domain.repository

import com.chscorp.cosmeticsstore.domain.product.ProductInfo
import com.chscorp.cosmeticsstore.domain.product.ProductListItem
import com.chscorp.cosmeticsstore.domain.util.Resource

interface ProductsRepository {
    suspend fun getProductListItem() : Resource<List<ProductListItem>>
    suspend fun getProductInfo() : Resource<List<ProductInfo>>
}