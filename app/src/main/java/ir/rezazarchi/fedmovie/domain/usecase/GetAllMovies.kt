package ir.rezazarchi.fedmovie.domain.usecase

import ir.rezazarchi.fedmovie.domain.repo.GetAllMoviesRepository

class GetAllMovies(private val allMoviesRepository: GetAllMoviesRepository) {
    suspend operator fun invoke() = allMoviesRepository.getAllMovies()
}