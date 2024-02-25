package com.chscorp.cosmeticsstore.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.chscorp.cosmeticsstore.domain.product.ProductListItem
import com.chscorp.cosmeticsstore.presentation.ui.theme.CosmeticsStoreTheme
import com.chscorp.cosmeticsstore.presentation.ui.theme.DeepPeach
import com.chscorp.cosmeticsstore.presentation.ui.theme.WengeGray

@Composable
fun CardProductItem(
    product: ProductListItem,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp
) {
    Card(
        modifier
            .fillMaxWidth()
            .heightIn(100.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(DeepPeach)
                .padding(16.dp)
        ) {
            Row {
                Text(text = "Marca: ", fontWeight = FontWeight.Bold)
                Text(
                    text = product.brand ?: "marca indisponivel",
                    color = WengeGray
                )
                Spacer(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight())
                Icon(Icons.Default.FavoriteBorder, contentDescription = null)
            }
            Row {
                Text(text = "Preço: ", fontWeight = FontWeight.Bold)
                Text(
                    text = product.price ?: "preço indisponivel",
                    color = WengeGray
                )
            }
            Row {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Descrição: ")
                        }
                        withStyle(style = SpanStyle(color = WengeGray)) {
                            append(product.description ?: "descrição indisponivel")
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun CardProductItemPreview() {
    CosmeticsStoreTheme {
        Surface {
            CardProductItem(
                product = ProductListItem(
                    id = null,
                    brand = "Teste",
                    price = "1.99",
                    description = LoremIpsum(5).values.first()
                )
            )
        }
    }
}