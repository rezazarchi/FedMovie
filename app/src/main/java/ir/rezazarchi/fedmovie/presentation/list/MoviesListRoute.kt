package ir.rezazarchi.fedmovie.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ir.rezazarchi.fedmovie.common.utils.ObserveAsEvents
import ir.rezazarchi.fedmovie.common.utils.UiText
import ir.rezazarchi.fedmovie.presentation.list.viewmodel.ListViewEffects
import ir.rezazarchi.fedmovie.presentation.list.viewmodel.ListViewEvents
import ir.rezazarchi.fedmovie.presentation.list.viewmodel.MoviesListViewModel

@Composable
fun MoviesListRoute(
    modifier: Modifier = Modifier,
    viewModel: MoviesListViewModel,
    showSnackBar: (UiText) -> Unit,
    showMovie: (Long) -> Unit,
    toggleBasket: (Long, Boolean) -> Unit,
) {
    ObserveAsEvents(viewModel.uiEvent) {
        when (it) {
            is ListViewEffects.ShowSnackBar -> showSnackBar(it.message)

        }
    }
    MoviesListScreen(
        modifier = modifier,
        state = viewModel.state,
        onToggleBasket = { movieId, addedToBasket ->
            toggleBasket(movieId, addedToBasket)
        },
        loadListData = {
            viewModel.onEvent(ListViewEvents.LoadMoviesList)
        },
        onMovieClick = showMovie,
    )
}