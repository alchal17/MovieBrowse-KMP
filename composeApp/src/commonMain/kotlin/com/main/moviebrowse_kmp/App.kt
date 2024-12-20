package com.main.moviebrowse_kmp

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.main.moviebrowse_kmp.viewmodels.MovieViewModel
import com.main.moviebrowse_kmp.views.screens.MovieInfoPage
import com.main.moviebrowse_kmp.views.screens.MoviesListPage
import com.main.moviebrowse_kmp.views.screens.Routes
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Preview
fun App() {
    val lazyVerticalGridState = rememberLazyGridState()
    val movieViewModel = koinInject<MovieViewModel>()
    val navController = rememberNavController()

    MaterialTheme(colors = lightColors()) {
        SharedTransitionLayout {
            NavHost(
                navController = navController,
                startDestination = Routes.MovieList,
                modifier = Modifier.background(Color(0xff535353))
            ) {
                composable<Routes.MovieList> {
                    MoviesListPage(
                        moviesViewModel = movieViewModel,
                        lazyVerticalGridState = lazyVerticalGridState,
                        animatedVisibilityScope = this,
                        cardOnClick = { posterPath: String?,
                                        id: String,
                                        title: String,
                                        overview: String,
                                        releaseDate: String,
                                        originalLanguage: String ->
                            navController.navigate(
                                Routes.MovieInfo(
                                    posterPath,
                                    id,
                                    title,
                                    overview,
                                    releaseDate,
                                    originalLanguage
                                )
                            )
                        }
                    )
                }
                composable<Routes.MovieInfo> {
                    val args = it.toRoute<Routes.MovieInfo>()
                    MovieInfoPage(
                        posterPath = args.posterPath,
                        id = args.id,
                        title = args.title,
                        overview = args.overview,
                        releaseDate = args.releaseDate,
                        originalLanguage = args.originalLanguage,
                        animatedVisibilityScope = this,
                        floatingActionButtonClick = { navController.navigateUp() }
                    )

                }
            }
        }
    }
}