package com.main.moviebrowse_kmp.network

import io.ktor.client.HttpClient

abstract class BaseService(protected val client: HttpClient) {
    protected val serverPath = "https://api.themoviedb.org"
}