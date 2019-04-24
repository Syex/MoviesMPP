package de.moviesmpp.domain.usecase

import de.moviesmpp.data.MoviesApi
import de.moviesmpp.domain.model.*

/**
 * A `use case` to get the currently most popular movies.
 */
class GetPopularMovies(private val moviesApi: MoviesApi) : UseCase<PopularMovies, UseCase.None>() {

    override suspend fun run(params: None): Either<Exception, PopularMovies> {
        return try {
            Success(moviesApi.getPopularMovies().toModel())
        } catch (e: Exception) {
            Failure(e)
        }
    }
}