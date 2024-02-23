package com.chscorp.cosmeticsstore.data.repository

import com.chscorp.cosmeticsstore.data.mappers.toListProductItem
import com.chscorp.cosmeticsstore.data.remote.MakeupApi
import com.chscorp.cosmeticsstore.domain.product.ProductListItem
import com.chscorp.cosmeticsstore.domain.repository.ProductsRepository
import com.chscorp.cosmeticsstore.domain.util.Resource

class ProductsRepositoryImpl(
    private val api: MakeupApi
): ProductsRepository {
    override suspend fun getProductData(): Resource<List<ProductListItem>> {
        return try {
            Resource.Success(
                data = api.getProducts().toListProductItem()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred. ")
        }
    }
}