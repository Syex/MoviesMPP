@file:Suppress("MemberVisibilityCanBePrivate")

package de.moviesmpp

import de.moviesmpp.data.MoviesApi
import de.moviesmpp.domain.usecase.GetPopularMovies
import de.moviesmpp.presentation.popularmovies.PopularMoviesPresenter
import io.ktor.client.engine.HttpClientEngine
import kotlin.native.concurrent.ThreadLocal

/**
 * A basic service locator implementation, as any frameworks like `Kodein` don't really work at the moment.
 */
@ThreadLocal
object ServiceLocator {

    val moviesApi by lazy { MoviesApi(PlatformServiceLocator.httpClientEngine) }

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
