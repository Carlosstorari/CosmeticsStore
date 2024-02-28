package com.chscorp.cosmeticsstore.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chscorp.cosmeticsstore.domain.product.ProductListItem
import com.chscorp.cosmeticsstore.presentation.PresentationConst.brandLabel
import com.chscorp.cosmeticsstore.presentation.PresentationConst.brandUnavailable
import com.chscorp.cosmeticsstore.presentation.PresentationConst.descriptionLabel
import com.chscorp.cosmeticsstore.presentation.PresentationConst.descriptionUnavailable
import com.chscorp.cosmeticsstore.presentation.PresentationConst.priceLabel
import com.chscorp.cosmeticsstore.presentation.PresentationConst.priceUnavailable
import com.chscorp.cosmeticsstore.presentation.ui.theme.CosmeticsStoreTheme
import com.chscorp.cosmeticsstore.presentation.ui.theme.DeepPeach
import com.chscorp.cosmeticsstore.presentation.ui.theme.WengeGray

@Composable
fun CardProductItem(
    product: ProductListItem,
    navController: NavController,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    onFavoriteClicked: (String) -> Unit,
    favoriteState: MutableMap<String, Boolean>
) {
    Card(
        modifier
            .fillMaxWidth()
            .heightIn(100.dp)
            .clickable {
                navController.navigate(route = "ProductDetail/${product.id}")
            },
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
                Text(text = brandLabel, fontWeight = FontWeight.Bold)
                Text(
                    text = product.brand ?: brandUnavailable,
                    color = WengeGray
                )
                Spacer(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight())
                FavoriteButton(
                    onFavoriteClicked = {
                        onFavoriteClicked(product.id)
                    },
                    favoriteState = favoriteState,
                    itemId = product.id
                )
            }
            Row {
                Text(text = priceLabel, fontWeight = FontWeight.Bold)
                Text(
                    text = product.price ?: priceUnavailable,
                    color = WengeGray
                )
            }
            Row {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(descriptionLabel)
                        }
                        withStyle(style = SpanStyle(color = WengeGray)) {
                            append(product.description ?: descriptionUnavailable)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63),
    onFavoriteClicked: (favorite: Boolean) -> Unit,
    favoriteState: MutableMap<String, Boolean>,
    itemId: String
) {
    val isFavorite = favoriteState[itemId]
    isFavorite?.let{
        IconToggleButton(
            checked = isFavorite,
            onCheckedChange = {
                onFavoriteClicked(isFavorite)
            }
        ) {
            Icon(
                tint = color,
                modifier = modifier.graphicsLayer {
                    scaleX = 1.3f
                    scaleY = 1.3f
                },
                imageVector = if (isFavorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = null
            )
        }
    }

}

@Preview
@Composable
fun CardProductItemPreview() {
    CosmeticsStoreTheme {
        Surface {
//            CardProductItem(
//                product = ProductListItem(
//                    id = null,
//                    brand = "Teste",
//                    price = "1.99",
//                    description = LoremIpsum(5).values.first()
//                )
//            )
        }
    }
}