package de.moviesmpp.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.URLProtocol

private const val BASE_URL = "api.themoviedb.org/4"
private const val HEADER_AUTHORIZATION = "Authorization"

class MoviesApi(clientEngine: HttpClientEngine) {

    private val client = HttpClient(clientEngine)

    suspend fun getPopularMovies(): String {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                encodedPath = "/discover/movie?sort_by=popularity.desc"
                header(HEADER_AUTHORIZATION, API_KEY.asBearerToken())
            }
        }
    }
}

private fun String.asBearerToken() = "Bearer $this"