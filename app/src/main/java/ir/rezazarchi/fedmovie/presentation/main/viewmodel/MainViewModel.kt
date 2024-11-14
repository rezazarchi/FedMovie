package ir.rezazarchi.fedmovie.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import ir.rezazarchi.fedmovie.domain.repo.BasketRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val basketRepository: BasketRepository) : ViewModel() {

    fun addMovieId(movieId: Long) {
        basketRepository.addMovieId(movieId)
    }

    fun removeMovieId(movieId: Long) {
        basketRepository.removeMovieId(movieId)
    }

    fun getAllInBasketMovies(): Flow<List<Long>> {
        return basketRepository.getAllInBasketMovies()
    }

    fun toggleBasket(movieId: Long, addToBasket: Boolean) {
        if (addToBasket) {
            addMovieId(movieId)
        } else {
            removeMovieId(movieId)
        }
    }

}