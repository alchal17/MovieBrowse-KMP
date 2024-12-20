package com.main.moviebrowse_kmp.viewmodels

import androidx.lifecycle.ViewModel
import com.main.moviebrowse_kmp.models.Movie
import com.main.moviebrowse_kmp.network.PaginationService
import com.main.moviebrowse_kmp.network.ServerResponse
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class MovieViewModel(
    private val apiRepository: PaginationService<ServerResponse<Movie>>
) :
    ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    private val _moviesFetching = MutableStateFlow(false)
    val moviesFetching = _moviesFetching.asStateFlow()

    private var currentPage = atomic(0)


    suspend fun addMovies() {
        _moviesFetching.value = true
        when (val response = apiRepository.getByPage(currentPage.value)) {
            is ServerResponse.Error -> {
            }

            is ServerResponse.Success -> {
                _movies.value += response.results
                currentPage.incrementAndGet()
            }
        }
        _moviesFetching.value = false
    }
}