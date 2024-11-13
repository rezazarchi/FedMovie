package ir.rezazarchi.fedmovie.data.repo

import ir.rezazarchi.fedmovie.data.mapper.mapToMovie
import ir.rezazarchi.fedmovie.data.remote.GetMovieApi
import ir.rezazarchi.fedmovie.data.remote.dto.MovieDto
import ir.rezazarchi.fedmovie.data.remote.utils.safeCall
import ir.rezazarchi.fedmovie.domain.repo.BasketRepository
import ir.rezazarchi.fedmovie.domain.repo.GetEachMovieRepository
import ir.rezazarchi.fedmovie.domain.repo.model.Movie
import ir.rezazarchi.fedmovie.domain.utils.NetworkError
import ir.rezazarchi.fedmovie.domain.utils.Result
import ir.rezazarchi.fedmovie.domain.utils.map
import kotlinx.coroutines.flow.last

class GetEachMovieRepositoryImpl(
    private val api: GetMovieApi,
    private val basketRepository: BasketRepository,
) : GetEachMovieRepository {
    override suspend fun getMovie(movieId: Long): Result<Movie, NetworkError> {
        val addedToBasket = basketRepository.getAllInBasketMovies().last().contains(movieId)
        return safeCall<MovieDto> {
            api.getMovieDetails(movieId)
        }.map {
            it.mapToMovie(addedToBasket = addedToBasket)
        }
    }
}
