package ir.rezazarchi.fedmovie.presentation.detail.viewmodel

import ir.rezazarchi.fedmovie.common.utils.UiText
import ir.rezazarchi.fedmovie.domain.repo.model.Movie

data class DetailsViewState(
    val movie: Movie?,
    val isLoading: Boolean,
    val error: UiText?,
)

sealed class DetailsViewEffects {
    data class ShowSnackBar(val message: UiText) : DetailsViewEffects()
}

sealed class DetailsViewEvents {
    data class LoadMovieInfo(val movieId: Long) : DetailsViewEvents()

}