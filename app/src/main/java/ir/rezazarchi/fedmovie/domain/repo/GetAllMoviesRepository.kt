package ir.rezazarchi.fedmovie.domain.repo

import ir.rezazarchi.fedmovie.domain.repo.model.Movie
import ir.rezazarchi.fedmovie.domain.utils.NetworkError
import ir.rezazarchi.fedmovie.domain.utils.Result

interface GetAllMoviesRepository {

    suspend fun getAllMovies(): Result<List<Movie>, NetworkError>

}