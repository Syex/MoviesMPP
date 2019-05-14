package de.moviesmpp

import de.moviesmpp.data.MoviesApi
import de.moviesmpp.domain.usecase.GetPopularMovies
import de.moviesmpp.presentation.popularmovies.PopularMoviesPresenter
import io.ktor.client.engine.HttpClientEngine
import kotlin.native.concurrent.ThreadLocal

// We can't move these inside ServiceLocator as there is currently a bug with Ktor objects being inside a Kotlin object
// See https://github.com/ktorio/ktor/issues/887
val moviesApi by lazy { MoviesApi(httpClientEngineWorkaround) }
expect val httpClientEngineWorkaround: HttpClientEngine

/**
 * A basic service locator implementation, as any frameworks like `Kodein` don't really work at the moment.
 */
object ServiceLocator {

    @ThreadLocal
    val getPopularMovies: GetPopularMovies
        get() = GetPopularMovies(moviesApi)

    val popularMoviesPresenter: PopularMoviesPresenter
        get() = PopularMoviesPresenter(getPopularMovies)
}

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
expect object PlatformServiceLocator {

    val httpClientEngine: HttpClientEngine
}
