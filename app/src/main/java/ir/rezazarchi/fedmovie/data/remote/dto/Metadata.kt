package ir.rezazarchi.fedmovie.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Metadata(
    @SerialName("current_page")
    val currentPage: String?,
    @SerialName("per_page")
    val perPage: Int?,
    @SerialName("page_count")
    val pageCount: Int?,
    @SerialName("total_count")
    val totalCount: Int?
)