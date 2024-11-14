package ir.rezazarchi.fedmovie.presentation.list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.rezazarchi.fedmovie.common.utils.toUiText
import ir.rezazarchi.fedmovie.domain.repo.BasketRepository
import ir.rezazarchi.fedmovie.domain.usecase.GetAllMovies
import ir.rezazarchi.fedmovie.domain.utils.onError
import ir.rezazarchi.fedmovie.domain.utils.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val getAllMovies: GetAllMovies,
    private val basketRepository: BasketRepository
) : ViewModel() {

    var state by mutableStateOf(
        ListViewState(
            emptyList(),
            false,
            emptyList(),
            null,
        )
    )

    private val _uiEvent = Channel<ListViewEffects>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            basketRepository.getAllInBasketMovies().collect {
                loadAllMovies()
            }
        }
    }

    fun onEvent(event: ListViewEvents) {
        when (event) {
            is ListViewEvents.LoadMoviesList -> {
                loadAllMovies()
            }
        }
    }

    private fun loadAllMovies() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            getAllMovies()
                .onSuccess {
                    state = state.copy(movies = it)
                    state = state.copy(isLoading = false)
                }
                .onError {
                    state = state.copy(error = it.toUiText(), isLoading = false)
                    _uiEvent.send(ListViewEffects.ShowSnackBar(it.toUiText()))
                }
        }
    }

}