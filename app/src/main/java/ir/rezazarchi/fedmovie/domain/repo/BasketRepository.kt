package ir.rezazarchi.fedmovie.domain.repo

import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    fun addMovieId(movieId: Long)
    fun removeMovieId(movieId: Long)
    fun getAllInBasketMovies(): Flow<List<Long>>
}