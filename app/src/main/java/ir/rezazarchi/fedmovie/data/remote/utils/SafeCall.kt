package ir.rezazarchi.fedmovie.data.remote.utils

import ir.rezazarchi.fedmovie.domain.utils.NetworkError
import ir.rezazarchi.fedmovie.domain.utils.Result
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException


suspend inline fun <reified T> safeCall(
    execute: () -> Response<T>
): Result<T, NetworkError> {
    return try {
        val response = execute()
        responseToResult(response)
    } catch (e: HttpException) {
        Result.Error(NetworkError.SERVER_ERROR)
    } catch (e: SocketTimeoutException) {
        Result.Error(NetworkError.REQUEST_TIMEOUT)
    } catch (e: IOException) {
        Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
        Result.Error(NetworkError.SERIALIZATION)
    } catch (e: Exception) {
        Result.Error(NetworkError.UNKNOWN)
    }
}

suspend inline fun <reified T> responseToResult(
    response: Response<T>
): Result<T, NetworkError> {
    return if (response.isSuccessful) {
        response.body()?.let {
            Result.Success(it)
        } ?: Result.Error(NetworkError.NULL_RESPONSE)
    } else {
        when (response.code()) {
            400 -> Result.Error(NetworkError.BAD_REQUEST)
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            403 -> Result.Error(NetworkError.FORBIDDEN)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}