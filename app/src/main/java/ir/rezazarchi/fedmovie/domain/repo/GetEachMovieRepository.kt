package ir.rezazarchi.fedmovie.domain.repo

import ir.rezazarchi.fedmovie.domain.repo.model.Movie
import ir.rezazarchi.fedmovie.domain.utils.NetworkError
import ir.rezazarchi.fedmovie.domain.utils.Result

interface GetEachMovieRepository {
    suspend fun getMovie(movieId: Long): Result<Movie, NetworkError>
}