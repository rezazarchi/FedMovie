package ir.rezazarchi.fedmovie.presentation.di

import ir.rezazarchi.fedmovie.data.di.RETROFIT
import ir.rezazarchi.fedmovie.data.local.BasketRepositoryImpl
import ir.rezazarchi.fedmovie.data.remote.GetAllMoviesApi
import ir.rezazarchi.fedmovie.data.remote.GetMovieApi
import ir.rezazarchi.fedmovie.data.repo.GetAllMoviesRepositoryImpl
import ir.rezazarchi.fedmovie.data.repo.GetEachMovieRepositoryImpl
import ir.rezazarchi.fedmovie.domain.repo.BasketRepository
import ir.rezazarchi.fedmovie.domain.repo.GetAllMoviesRepository
import ir.rezazarchi.fedmovie.domain.repo.GetEachMovieRepository
import ir.rezazarchi.fedmovie.domain.usecase.GetAllMovies
import ir.rezazarchi.fedmovie.domain.usecase.GetMovieDetails
import ir.rezazarchi.fedmovie.presentation.detail.viewmodel.MoviesDetailsViewModel
import ir.rezazarchi.fedmovie.presentation.list.viewmodel.MoviesListViewModel
import ir.rezazarchi.fedmovie.presentation.main.viewmodel.MainViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val presentationModules = module {

    single {
        get<Retrofit>(named(RETROFIT)).create(GetAllMoviesApi::class.java)
    }

    single {
        get<Retrofit>(named(RETROFIT)).create(GetMovieApi::class.java)
    }

    singleOf(::BasketRepositoryImpl) bind BasketRepository::class
    singleOf(::GetAllMoviesRepositoryImpl) bind GetAllMoviesRepository::class
    singleOf(::GetEachMovieRepositoryImpl) bind GetEachMovieRepository::class

    factoryOf(::GetAllMovies)
    factoryOf(::GetMovieDetails)

    viewModelOf(::MainViewModel)
    viewModelOf(::MoviesListViewModel)
    viewModelOf(::MoviesDetailsViewModel)


}