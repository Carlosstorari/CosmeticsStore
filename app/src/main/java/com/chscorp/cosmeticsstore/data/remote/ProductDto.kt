package com.chscorp.cosmeticsstore.data.remote

import com.squareup.moshi.Json

data class ProductDto(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "brand")
    val brand: String,
    @field:Json(name = "price")
    val price: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "category")
    val category: String,
    @field:Json(name = "product_type")
    val type: String,
    @field:Json(name = "rating")
    val rating: String

)
