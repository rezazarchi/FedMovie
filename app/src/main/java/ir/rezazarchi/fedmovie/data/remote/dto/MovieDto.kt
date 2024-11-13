package ir.rezazarchi.fedmovie.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class MovieDto(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String?,
    @SerialName("poster")
    val poster: String?,
    @SerialName("year")
    val year: String?,
    @SerialName("rated")
    val rated: String?,
    @SerialName("released")
    val released: String?,
    @SerialName("runtime")
    val runtime: String?,
    @SerialName("director")
    val director: String?,
    @SerialName("writer")
    val writer: String?,
    @SerialName("actors")
    val actors: String?,
    @SerialName("plot")
    val plot: String?,
    @SerialName("country")
    val country: String?,
    @SerialName("awards")
    val awards: String?,
    @SerialName("metascore")
    val metascore: String?,
    @SerialName("imdb_rating")
    val imdbRating: String?,
    @SerialName("imdb_votes")
    val imdbVotes: String?,
    @SerialName("imdb_id")
    val imdbId: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("genres")
    val genres: List<String?>?,
    @SerialName("images")
    val images: List<String?>?
)