package com.chscorp.cosmeticsstore.data.remote

import retrofit2.http.GET

interface MakeupApi {

    @GET("v1/products.json")
    suspend fun getProducts(): List<ProductDto>
}