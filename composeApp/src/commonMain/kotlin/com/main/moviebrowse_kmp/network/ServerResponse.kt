package com.main.moviebrowse_kmp.network

sealed interface ServerResponse<out T> {
    data class Success<T>(val results: List<T>) : ServerResponse<T>
    data class Error(val message: String) : ServerResponse<Nothing>
}