package com.main.moviebrowse_kmp.network

interface PaginationService<T> {
    suspend fun getByPage(pageNumber: Int): T
}