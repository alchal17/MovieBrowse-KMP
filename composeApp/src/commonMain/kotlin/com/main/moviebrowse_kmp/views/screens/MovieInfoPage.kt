package com.main.moviebrowse_kmp.views.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
expect fun SharedTransitionScope.MovieInfoPage(
    posterPath: String?,
    id: String,
    title: String,
    overview: String,
    releaseDate: String,
    originalLanguage: String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    floatingActionButtonClick: () -> Unit
)