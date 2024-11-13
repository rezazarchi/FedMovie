package ir.rezazarchi.fedmovie.data.repo

import ir.rezazarchi.fedmovie.data.mapper.mapToMoviesList
import ir.rezazarchi.fedmovie.data.remote.GetAllMoviesApi
import ir.rezazarchi.fedmovie.data.remote.dto.MoviesResponse
import ir.rezazarchi.fedmovie.data.remote.utils.safeCall
import ir.rezazarchi.fedmovie.domain.repo.GetAllMoviesRepository
import ir.rezazarchi.fedmovie.domain.repo.model.Movie
import ir.rezazarchi.fedmovie.domain.utils.NetworkError
import ir.rezazarchi.fedmovie.domain.utils.Result
import ir.rezazarchi.fedmovie.domain.utils.map

class GetAllMoviesRepositoryImpl(private val getAllApi: GetAllMoviesApi) : GetAllMoviesRepository {
    override suspend fun getAllMovies(): Result<List<Movie>, NetworkError> {
        return safeCall<MoviesResponse> {
            getAllApi.getMovies()
        }.map(MoviesResponse::mapToMoviesList)
    }
}