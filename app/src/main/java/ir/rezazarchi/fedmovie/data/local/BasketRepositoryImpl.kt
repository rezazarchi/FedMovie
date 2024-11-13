package ir.rezazarchi.fedmovie.data.local

import ir.rezazarchi.fedmovie.domain.repo.BasketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BasketRepositoryImpl : BasketRepository {

    private val _movieIds = MutableStateFlow<List<Long>>(emptyList())

    override fun addMovieId(movieId: Long) {
        _movieIds.value += movieId
    }

    override fun removeMovieId(movieId: Long) {
        _movieIds.update {
            _movieIds.value.filterNot { it == movieId }
        }
    }

    override fun getAllInBasketMovies(): Flow<List<Long>> {
        return _movieIds.asStateFlow()
    }
}