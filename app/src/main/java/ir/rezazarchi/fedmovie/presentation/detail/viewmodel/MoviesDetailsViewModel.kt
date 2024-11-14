package ir.rezazarchi.fedmovie.presentation.detail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.rezazarchi.fedmovie.common.utils.toUiText
import ir.rezazarchi.fedmovie.domain.repo.BasketRepository
import ir.rezazarchi.fedmovie.domain.usecase.GetMovieDetails
import ir.rezazarchi.fedmovie.domain.utils.onError
import ir.rezazarchi.fedmovie.domain.utils.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MoviesDetailsViewModel(
    private val movieDetails: GetMovieDetails, basketRepository: BasketRepository
) : ViewModel() {

    var state by mutableStateOf(
        DetailsViewState(
            movie = null,
            isLoading = false,
            error = null,
        )
    )

    private val _uiEvent = Channel<DetailsViewEffects>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            basketRepository.getAllInBasketMovies().collect { inBasketMovies ->
                state.movie?.let { movie ->
                    state =
                        state.copy(movie = movie.copy(addedToBasket = inBasketMovies.contains(movie.id)))
                }
            }
        }
    }

    fun onEvent(event: DetailsViewEvents) {
        when (event) {
            is DetailsViewEvents.LoadMovieInfo -> {
                loadMovie(event.movieId)
            }
        }
    }

    private fun loadMovie(movieId: Long) {
        viewModelScope.launch {
            movieDetails.getMovieDetails(movieId).onSuccess {
                state = state.copy(movie = it)
            }.onError {
                state = state.copy(error = it.toUiText())
                _uiEvent.send(DetailsViewEffects.ShowSnackBar(it.toUiText()))
            }
        }
    }
}