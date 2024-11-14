package ir.rezazarchi.fedmovie.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ir.rezazarchi.fedmovie.common.utils.ObserveAsEvents
import ir.rezazarchi.fedmovie.common.utils.UiText
import ir.rezazarchi.fedmovie.presentation.detail.viewmodel.DetailsViewEffects
import ir.rezazarchi.fedmovie.presentation.detail.viewmodel.DetailsViewEvents
import ir.rezazarchi.fedmovie.presentation.detail.viewmodel.MoviesDetailsViewModel

@Composable
fun MovieDetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: MoviesDetailsViewModel,
    movieId: Long,
    showSnackBar: (UiText) -> Unit,
    toggleBasket: (Long, Boolean) -> Unit,
) {
    ObserveAsEvents(viewModel.uiEvent) {
        when (it) {
            is DetailsViewEffects.ShowSnackBar -> showSnackBar(it.message)
        }
    }

    MovieDetailsScreen(
        modifier = modifier,
        id = movieId,
        state = viewModel.state,
        loadData = {
            viewModel.onEvent(DetailsViewEvents.LoadMovieInfo(movieId))
        },
        onToggleBasket = { id, addedToBasket ->
            toggleBasket(id, addedToBasket)
        }
    )
}