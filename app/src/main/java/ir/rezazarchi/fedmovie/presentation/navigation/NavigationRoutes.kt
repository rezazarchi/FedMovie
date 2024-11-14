package ir.rezazarchi.fedmovie.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface NavigationRoutes {
    @Serializable
    data object MoviesList : NavigationRoutes

    @Serializable
    data class MovieDetails(val movieId: Long) : NavigationRoutes

}