@file:OptIn(ExperimentalGlideComposeApi::class)

package ir.rezazarchi.fedmovie.presentation.detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ir.rezazarchi.fedmovie.R
import ir.rezazarchi.fedmovie.featureflag.BasketFeature
import ir.rezazarchi.fedmovie.presentation.detail.viewmodel.DetailsViewState

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    id: Long,
    state: DetailsViewState,
    loadData: (Long) -> Unit,
    onToggleBasket: (Long, Boolean) -> Unit,
) {

    LaunchedEffect(Unit) {
        loadData(id)
    }

    val basketVisible by remember {
        mutableStateOf(BasketFeature.isBasketVisible())
    }


    state.movie?.let { movie ->
        LazyColumn(
            modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    model = movie.poster,
                    contentDescription = movie.title,
                )
            }
            item {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = movie.title.toString(),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            item {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = movie.plot.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            if (basketVisible)
                item {
                    Button(modifier = Modifier.padding(8.dp), onClick = {
                        onToggleBasket(movie.id, !movie.addedToBasket)
                    }) {
                        Icon(
                            imageVector = if (movie.addedToBasket) {
                                Icons.Filled.ShoppingCart
                            } else {
                                Icons.Outlined.ShoppingCart
                            }, contentDescription = if (movie.addedToBasket) {
                                stringResource(R.string.remove_from_basket)
                            } else {
                                stringResource(R.string.add_to_basket)
                            }
                        )
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Text(
                            text = if (movie.addedToBasket) {
                                stringResource(R.string.added_to_basket)
                            } else {
                                stringResource(R.string.add_to_basket)
                            },
                            color = if (movie.addedToBasket) {
                                Color.Green
                            } else {
                                MaterialTheme.colorScheme.inverseOnSurface
                            },
                            fontWeight = if (movie.addedToBasket) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            }
                        )
                    }
                }
        }
    }

}