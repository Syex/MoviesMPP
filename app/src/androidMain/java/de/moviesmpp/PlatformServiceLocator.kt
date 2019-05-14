package de.moviesmpp

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.logging.HttpLoggingInterceptor

// Currently only needed as a workaround for Kotlin/Native, so we delegate the call to the correct property
actual val httpClientEngineWorkaround: HttpClientEngine by lazy { PlatformServiceLocator.httpClientEngine }

actual object PlatformServiceLocator {

    actual val httpClientEngine: HttpClientEngine by lazy {
        OkHttp.create {
            val networkInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            addNetworkInterceptor(networkInterceptor)
        }
    }
}