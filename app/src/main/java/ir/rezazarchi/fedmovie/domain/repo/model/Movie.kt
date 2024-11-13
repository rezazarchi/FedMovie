package ir.rezazarchi.fedmovie.domain.repo.model


import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Movie(
    val id: Long,
    val title: String?,
    val poster: String?,
    val year: String?,
    val rated: String?,
    val released: String?,
    val runtime: String?,
    val director: String?,
    val writer: String?,
    val actors: String?,
    val plot: String?,
    val country: String?,
    val awards: String?,
    val metascore: String?,
    val imdbRating: String?,
    val imdbVotes: String?,
    val imdbId: String?,
    val type: String?,
    val genres: List<String?>?,
    val images: List<String?>?,
    val addedToBasket: Boolean = false,
)