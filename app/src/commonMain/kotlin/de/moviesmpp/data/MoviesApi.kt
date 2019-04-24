package de.moviesmpp.data

import de.moviesmpp.data.entity.PopularMoviesEntity
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol

private const val BASE_URL = "api.themoviedb.org/4"
private const val HEADER_AUTHORIZATION = "Authorization"

class MoviesApi(clientEngine: HttpClientEngine) {

    private val client = HttpClient(clientEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getPopularMovies(): PopularMoviesEntity {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                encodedPath = "/discover/movie"
                parameter("sort_by", "popularity.desc")
                header(HEADER_AUTHORIZATION, API_KEY.asBearerToken())
            }
        }
    }
}

private fun String.asBearerToken() = "Bearer $this"