package de.moviesmpp

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.ios.Ios

actual val httpClientEngineWorkaround: HttpClientEngine by lazy { Ios.create() }

/**
 * Contains some expected dependencies for the [ServiceLocator] that have to be resolved by Android/iOS.
 */
actual object PlatformServiceLocator {

    actual val httpClientEngine: HttpClientEngine by lazy { Ios.create() }
}