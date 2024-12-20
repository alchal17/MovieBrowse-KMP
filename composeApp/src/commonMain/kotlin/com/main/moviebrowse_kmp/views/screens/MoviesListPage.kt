package com.main.moviebrowse_kmp.views.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.main.moviebrowse_kmp.viewmodels.MovieViewModel
import com.main.moviebrowse_kmp.views.elements.MainTopBar
import com.main.moviebrowse_kmp.views.elements.MovieCard
import com.main.moviebrowse_kmp.views.elements.minCardWidth

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MoviesListPage(
    moviesViewModel: MovieViewModel,
    lazyVerticalGridState: LazyGridState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    cardOnClick: (
        posterPath: String?,
        id: String,
        title: String,
        overview: String,
        releaseDate: String,
        originalLanguage: String
    ) -> Unit,
) {
    val movies by moviesViewModel.movies.collectAsState()
    val isLoading = moviesViewModel.moviesFetching.collectAsState()

    val isAtEndOfList by remember {
        derivedStateOf {
            val lastVisibleIndex =
                lazyVerticalGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            lastVisibleIndex == movies.size - 1
        }
    }
    Scaffold(topBar = { MainTopBar() }, backgroundColor = Color(0xff535353)) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = minCardWidth.dp),
            state = lazyVerticalGridState,
            verticalArrangement = Arrangement.spacedBy(15.dp), modifier = Modifier.fillMaxSize()
        ) {
            items(
                movies.size,
                key = { index -> movies[index].uniqueID }) {
                Box(modifier = Modifier.height(200.dp)) {
                    MovieCard(
                        movie = movies[it],
                        onClick = cardOnClick,
                        animatedVisibilityScope = animatedVisibilityScope
                    )

                }
            }
        }
    }
    LaunchedEffect(isAtEndOfList && !isLoading.value) {
        moviesViewModel.addMovies()
    }
}