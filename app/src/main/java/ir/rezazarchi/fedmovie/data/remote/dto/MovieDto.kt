package ir.rezazarchi.fedmovie.data.remote.dto


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class MovieDto(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String? = null,
    @SerialName("poster")
    val poster: String? = null,
    @SerialName("year")
    val year: String? = null,
    @SerialName("rated")
    val rated: String? = null,
    @SerialName("released")
    val released: String? = null,
    @SerialName("runtime")
    val runtime: String? = null,
    @SerialName("director")
    val director: String? = null,
    @SerialName("writer")
    val writer: String? = null,
    @SerialName("actors")
    val actors: String? = null,
    @SerialName("plot")
    val plot: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("awards")
    val awards: String? = null,
    @SerialName("metascore")
    val metascore: String? = null,
    @SerialName("imdb_rating")
    val imdbRating: String? = null,
    @SerialName("imdb_votes")
    val imdbVotes: String? = null,
    @SerialName("imdb_id")
    val imdbId: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("genres")
    val genres: List<String>? = null,
    @SerialName("images")
    val images: List<String>? = null,
)