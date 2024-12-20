package com.main.moviebrowse_kmp.network

import com.main.moviebrowse_kmp.API_KEY
import com.main.moviebrowse_kmp.models.Movie
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class MovieService(httpClient: HttpClient) : BaseService(httpClient), PaginationService<ServerResponse<Movie>> {

    @Serializable
    private data class MovieResponse(
        @SerialName("results") val results: List<Movie>
    )

    override suspend fun getByPage(pageNumber: Int): ServerResponse<Movie> {
        return try {
            val response: MovieResponse =
                client.get("${serverPath}/3/trending/movie/day?api_key=$API_KEY&page=${pageNumber + 1}").body()
            ServerResponse.Success(response.results)
        } catch (e: Exception) {
            return ServerResponse.Error(e.message ?: "Unknown error")
        }
    }

}