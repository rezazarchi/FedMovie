@file:OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)

package ir.rezazarchi.fedmovie.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ir.rezazarchi.fedmovie.R
import ir.rezazarchi.fedmovie.common.utils.ObserveAsEvents
import ir.rezazarchi.fedmovie.featureflag.BasketFeature
import ir.rezazarchi.fedmovie.presentation.list.viewmodel.ListViewEffects
import ir.rezazarchi.fedmovie.presentation.list.viewmodel.ListViewEvents
import ir.rezazarchi.fedmovie.presentation.list.viewmodel.ListViewState
import kotlinx.coroutines.flow.Flow

@Composable
fun MoviesListScreen(
    modifier: Modifier = Modifier,
    state: ListViewState,
    onToggleBasket: (Long, Boolean) -> Unit,
    loadListData: () -> Unit,
    onMovieClick: (Long) -> Unit,
) {

    LaunchedEffect(Unit) {
        loadListData()
    }

    val basketVisible by remember {
        mutableStateOf(BasketFeature.isBasketVisible())
    }

    PullToRefreshBox(
        modifier = modifier.fillMaxSize(),
        isRefreshing = state.isLoading,
        onRefresh = loadListData,
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.movies, key = { it.id }) { movie ->
                ListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onMovieClick(movie.id.toLong())
                        },
                    headlineContent = {
                        Text(text = movie.title.toString())
                    },
                    supportingContent = {
                        Text(
                            text = movie.year.toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    leadingContent = {
                        GlideImage(
                            model = movie.poster,
                            contentDescription = movie.title,
                        )
                    },
                    trailingContent = {
                        if(basketVisible) {
                            IconButton(
                                onClick = {
                                    onToggleBasket(movie.id, !movie.addedToBasket)
                                },
                            ) {
                                Icon(
                                    imageVector = if (movie.addedToBasket) Icons.Filled.ShoppingCart else Icons.Outlined.ShoppingCart,
                                    contentDescription = if (movie.addedToBasket) stringResource(R.string.remove_from_basket)
                                    else stringResource(R.string.add_to_basket)
                                )
                            }
                        }
                    },
                )
                HorizontalDivider()
            }
        }
    }

}

