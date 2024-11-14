package ir.rezazarchi.fedmovie.common.utils

import ir.rezazarchi.fedmovie.R
import ir.rezazarchi.fedmovie.domain.utils.NetworkError

fun NetworkError.toUiText(): UiText {
    return UiText.StringResource(
        when (this) {
            NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
            NetworkError.BAD_REQUEST -> R.string.error_bad_request
            NetworkError.UNAUTHORIZED -> R.string.error_unauthorized
            NetworkError.FORBIDDEN -> R.string.error_forbidden
            NetworkError.NULL_RESPONSE -> R.string.error_null_response
            NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
            NetworkError.NO_INTERNET -> R.string.error_no_internet
            NetworkError.SERVER_ERROR -> R.string.error_server_error
            NetworkError.SERIALIZATION -> R.string.error_serialization
            NetworkError.UNKNOWN -> R.string.error_serialization
        }
    )
}