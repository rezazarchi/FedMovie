package ir.rezazarchi.fedmovie.domain.usecase

import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.coEvery
import io.mockk.mockk
import ir.rezazarchi.fedmovie.data.local.BasketRepositoryImpl
import ir.rezazarchi.fedmovie.data.remote.GetMovieApi
import ir.rezazarchi.fedmovie.data.remote.dto.MovieDto
import ir.rezazarchi.fedmovie.data.repo.GetEachMovieRepositoryImpl
import ir.rezazarchi.fedmovie.domain.repo.BasketRepository
import ir.rezazarchi.fedmovie.domain.repo.GetEachMovieRepository
import ir.rezazarchi.fedmovie.domain.utils.NetworkError
import ir.rezazarchi.fedmovie.domain.utils.Result
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GetMovieDetailsTest {

    private lateinit var getMovieDetails: GetMovieDetails
    private lateinit var movieRepository: GetEachMovieRepository
    private lateinit var getMovieApi: GetMovieApi
    private lateinit var basketRepository: BasketRepository

    @Before
    fun setUp() {
        basketRepository = BasketRepositoryImpl()
        getMovieApi = mockk()
        movieRepository = GetEachMovieRepositoryImpl(
            getMovieApi,
            basketRepository,
        )
        getMovieDetails = GetMovieDetails(movieRepository)
    }

    @Test
    fun getMovieResultsSuccess() = runBlocking {
        coEvery { getMovieApi.getMovieDetails(1) } returns Response.success(MovieDto(1))
        val movie = getMovieDetails.getMovieDetails(1)
        assertThat(movie).isEqualTo(Result.Success(MovieDto(1)))
    }

    @Test
    fun getMovieResultsFailure() = runBlocking {
        coEvery { getMovieApi.getMovieDetails(1) } returns Response.error(
            403,
            mockk(relaxed = true)
        )
        val movieFailedResult = getMovieDetails.getMovieDetails(1)
        assertThat(movieFailedResult).isEqualTo(Result.Error(NetworkError.FORBIDDEN))
    }
}