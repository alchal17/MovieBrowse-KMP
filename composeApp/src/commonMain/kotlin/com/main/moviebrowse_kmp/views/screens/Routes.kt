package com.main.moviebrowse_kmp.views.screens

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    object MovieList : Routes

    @Serializable
    data class MovieInfo(
        val posterPath: String?,
        val id: String,
        val title: String,
        val overview: String,
        val releaseDate: String,
        val originalLanguage: String,
    ) : Routes
}
