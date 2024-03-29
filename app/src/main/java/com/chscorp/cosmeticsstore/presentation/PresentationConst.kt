package com.chscorp.cosmeticsstore.presentation

object PresentationConst {

    //list filter
    const val lowestPrice = "Menor preço"
    const val biggestPrice = "Maior preço"
    const val alphabetica = "A-Z"
    const val invertedAlphabetica = "Z-A"
    const val favorite = "Favoritos"
    val options = listOf(
        lowestPrice,
        biggestPrice,
        alphabetica,
        invertedAlphabetica,
        favorite
    )
    const val titleFilter = "Ordenar por"

    //card item
    const val brandLabel = "Marca: "
    const val brandUnavailable = "marca indisponível"
    const val priceLabel = "Preço: "
    const val priceUnavailable = "preço indisponível"
    const val descriptionLabel = "Descrição: "
    const val descriptionUnavailable = "descrição indisponível"

    //product info
    const val ratingUnavailable = "classificação indisponível"
    const val categoryUnavailable = "categoria indisponível"
    const val typeUnavailable = "tipo indisponível"
}