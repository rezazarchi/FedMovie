package ir.rezazarchi.fedmovie.presentation.list.viewmodel

import ir.rezazarchi.fedmovie.common.utils.UiText
import ir.rezazarchi.fedmovie.domain.repo.model.Movie

data class ListViewState(
    val movies: List<Movie>,
    val isLoading: Boolean,
    val inBasketsList: List<Long>,
    val error: UiText?,
)

sealed class ListViewEffects {
    data class ShowSnackBar(val message: UiText) : ListViewEffects()
}

sealed class ListViewEvents {
    data object LoadMoviesList : ListViewEvents()

}