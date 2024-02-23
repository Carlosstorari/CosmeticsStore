package com.chscorp.cosmeticsstore.domain.product

data class ProductInfo(
    val id: String,
    val brand: String,
    val price: String,
    val category: String,
    val type: String,
    val rating: String
)