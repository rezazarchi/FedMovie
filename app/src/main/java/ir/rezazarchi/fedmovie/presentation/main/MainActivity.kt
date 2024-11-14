package ir.rezazarchi.fedmovie.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ir.rezazarchi.fedmovie.presentation.detail.MovieDetailsRoute
import ir.rezazarchi.fedmovie.presentation.detail.viewmodel.MoviesDetailsViewModel
import ir.rezazarchi.fedmovie.presentation.list.MoviesListRoute
import ir.rezazarchi.fedmovie.presentation.list.viewmodel.MoviesListViewModel
import ir.rezazarchi.fedmovie.presentation.main.viewmodel.MainViewModel
import ir.rezazarchi.fedmovie.presentation.navigation.NavigationRoutes
import ir.rezazarchi.fedmovie.ui.theme.FedMovieTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val coroutineScope = rememberCoroutineScope()
            val context = LocalContext.current
            val snackbarHostState = remember { SnackbarHostState() }
            FedMovieTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }) { innerPadding ->
                    val navController = rememberNavController()
                    val mainViewModel by viewModel<MainViewModel>()
                    NavHost(
                        navController = navController,
                        startDestination = NavigationRoutes.MoviesList
                    ) {
                        composable<NavigationRoutes.MoviesList> {
                            val viewModel by viewModel<MoviesListViewModel>()
                            MoviesListRoute(
                                modifier = Modifier.padding(innerPadding),
                                viewModel = viewModel,
                                showSnackBar = {
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar(it.asString(context))
                                    }
                                },
                                showMovie = {
                                    navController.navigate(NavigationRoutes.MovieDetails(it))
                                },
                                toggleBasket = { movieId, addedToBasket ->
                                    mainViewModel.toggleBasket(movieId, addedToBasket)
                                }
                            )
                        }

                        composable<NavigationRoutes.MovieDetails> {
                            val movieId = it.toRoute<NavigationRoutes.MovieDetails>().movieId
                            val viewModel by viewModel<MoviesDetailsViewModel>()
                            MovieDetailsRoute(
                                modifier = Modifier.padding(innerPadding),
                                viewModel = viewModel,
                                movieId = movieId,
                                showSnackBar = {
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar(it.asString(context))
                                    }
                                },
                                toggleBasket = { id, addedToBasket ->
                                    mainViewModel.toggleBasket(id, addedToBasket)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}