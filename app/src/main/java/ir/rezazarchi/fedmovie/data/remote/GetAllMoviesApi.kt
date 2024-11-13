package ir.rezazarchi.fedmovie.data.remote

import ir.rezazarchi.fedmovie.data.remote.dto.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface GetAllMoviesApi {

    @GET("movies")
    suspend fun getMovies(): Response<MoviesResponse>

}