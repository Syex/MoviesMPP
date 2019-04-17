package de.moviesmpp

import de.moviesmpp.data.MoviesApi
import io.ktor.client.engine.HttpClientEngine

/**
 * A basic service locator implementation, as any frameworks like `Kodein` don't really work at the moment.
 */
object ServiceLocator {

    val moviesApi by lazy { MoviesApi(PlatformServiceLocator.httpClientEngine) }
}

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
expect object PlatformServiceLocator {

    val httpClientEngine: HttpClientEngine
}
