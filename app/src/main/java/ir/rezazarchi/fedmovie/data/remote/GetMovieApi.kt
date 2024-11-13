package ir.rezazarchi.fedmovie.data.remote

import ir.rezazarchi.fedmovie.data.remote.dto.MovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface GetMovieApi {

    @GET("movies/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Long): Response<MovieDto>

}