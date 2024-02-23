package com.chscorp.cosmeticsstore.data.remote

import retrofit2.http.GET

interface MakeupApi {

    @GET("v1/products.json")
    fun getProducts(): ProductsListDto
}