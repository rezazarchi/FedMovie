package ir.rezazarchi.fedmovie.domain.usecase

import ir.rezazarchi.fedmovie.domain.repo.GetEachMovieRepository


class GetMovieDetails(private val eachMovieRepository: GetEachMovieRepository) {
    suspend fun getMovieDetails(movieId: Long) = eachMovieRepository.getMovie(movieId)
}