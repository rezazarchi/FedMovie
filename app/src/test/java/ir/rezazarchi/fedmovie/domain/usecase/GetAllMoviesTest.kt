package ir.rezazarchi.fedmovie.domain.usecase


import assertk.assertThat
import assertk.assertions.isEqualTo
import io.mockk.coEvery
import io.mockk.mockk
import ir.rezazarchi.fedmovie.data.local.BasketRepositoryImpl
import ir.rezazarchi.fedmovie.data.remote.GetAllMoviesApi
import ir.rezazarchi.fedmovie.data.remote.dto.MovieDto
import ir.rezazarchi.fedmovie.data.remote.dto.MoviesResponse
import ir.rezazarchi.fedmovie.data.repo.GetAllMoviesRepositoryImpl
import ir.rezazarchi.fedmovie.domain.repo.BasketRepository
import ir.rezazarchi.fedmovie.domain.repo.GetAllMoviesRepository
import ir.rezazarchi.fedmovie.domain.utils.NetworkError
import ir.rezazarchi.fedmovie.domain.utils.Result
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GetAllMoviesTest {

    private lateinit var getAllMoviesUseCase: GetAllMovies
    private lateinit var allMoviesRepository: GetAllMoviesRepository
    private lateinit var getAllApi: GetAllMoviesApi
    private lateinit var basketRepository: BasketRepository

    @Before
    fun setUp() {
        basketRepository = BasketRepositoryImpl()
        getAllApi = mockk()
        allMoviesRepository = GetAllMoviesRepositoryImpl(getAllApi, basketRepository)
        getAllMoviesUseCase = GetAllMovies(allMoviesRepository)
    }

    @Test
    fun getAllMoviesSuccessfullyFromServer() = runBlocking {
        coEvery { getAllApi.getMovies() } returns Response.success(
            MoviesResponse(
                listOf(MovieDto(1), MovieDto(2)), null
            )
        )
        val allMovies = getAllMoviesUseCase.invoke()
        assertThat(allMovies).isEqualTo(Result.Success(listOf(MovieDto(1), MovieDto(2))))
    }

    @Test
    fun getAllMoviesFailedFromServer() = runBlocking {
        coEvery { getAllApi.getMovies() } returns Response.error(403, mockk(relaxed = true))
        val allMovies = getAllMoviesUseCase.invoke()
        assertThat(allMovies).isEqualTo(Result.Error(NetworkError.FORBIDDEN))

    }
}