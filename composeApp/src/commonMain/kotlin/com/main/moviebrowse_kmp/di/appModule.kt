package com.main.moviebrowse_kmp.di

import com.main.moviebrowse_kmp.models.Movie
import com.main.moviebrowse_kmp.network.MovieService
import com.main.moviebrowse_kmp.network.PaginationService
import com.main.moviebrowse_kmp.network.ServerResponse
import com.main.moviebrowse_kmp.network.client
import com.main.moviebrowse_kmp.viewmodels.MovieViewModel
import io.ktor.client.*
import org.koin.dsl.module

val appModule = module {
    single<HttpClient> { client }
    single<PaginationService<ServerResponse<Movie>>> { MovieService(get()) }
    factory { MovieViewModel(get()) }
}