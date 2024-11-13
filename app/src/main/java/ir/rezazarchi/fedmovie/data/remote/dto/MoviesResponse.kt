package ir.rezazarchi.fedmovie.data.remote.dto


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class MoviesResponse(
    @SerialName("data")
    val moviesData: List<MovieDto> = emptyList(),
    @SerialName("metadata")
    val metadata: Metadata?
)