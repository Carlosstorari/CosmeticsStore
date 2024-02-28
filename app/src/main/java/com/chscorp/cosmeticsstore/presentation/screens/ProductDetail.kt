package com.chscorp.cosmeticsstore.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chscorp.cosmeticsstore.presentation.PresentationConst.brandUnavailable
import com.chscorp.cosmeticsstore.presentation.PresentationConst.categoryUnavailable
import com.chscorp.cosmeticsstore.presentation.PresentationConst.priceUnavailable
import com.chscorp.cosmeticsstore.presentation.PresentationConst.ratingUnavailable
import com.chscorp.cosmeticsstore.presentation.PresentationConst.typeUnavailable
import com.chscorp.cosmeticsstore.presentation.viewModel.MainViewModel

@Composable
fun ProductDetailScreen(
    viewModel: MainViewModel,
    id: String?
) {
    val product = viewModel.getProductInfo(id)
    Column {
        Spacer(modifier = Modifier.padding(10.dp))
        Row(Modifier.padding(10.dp)) {
            Text(text = "Marca: ", fontWeight = FontWeight.Bold)
            Text(text = product?.brand ?: brandUnavailable)
        }

        Row(Modifier.padding(10.dp)) {
            Text(text = "Preço: ", fontWeight = FontWeight.Bold)
            Text(text = product?.price ?: priceUnavailable)
        }

        Row(Modifier.padding(10.dp)) {
            Text(text = "Classificação: ", fontWeight = FontWeight.Bold)
            Text(text = product?.rating ?: ratingUnavailable)
        }

        Row(Modifier.padding(10.dp)) {
            Text(text = "Categoria: ", fontWeight = FontWeight.Bold)
            Text(text = product?.category ?: categoryUnavailable)
        }

        Row(Modifier.padding(10.dp)) {
            Text(text = "Tipo de produto: ", fontWeight = FontWeight.Bold)
            Text(text = product?.type ?: typeUnavailable)
        }
    }
}