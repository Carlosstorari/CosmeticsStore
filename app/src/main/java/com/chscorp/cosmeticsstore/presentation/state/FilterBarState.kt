package com.chscorp.cosmeticsstore.presentation.state

data class FilterBarState(
    val onOptionSelected: (String) -> Unit = {},
    val orderByLowestPrice: () -> Unit = {},
    val orderByBiggestPrice: () -> Unit = {},
    val orderByAlphabetica: () -> Unit = {},
    val orderByInvertedAlphabetica: () -> Unit = {},
    val orderByFavorites: () -> Unit = {}

)
