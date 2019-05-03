package de.moviesmpp.presentation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import platform.Foundation.NSRunLoop
import platform.Foundation.performBlock
import kotlin.coroutines.CoroutineContext

actual val uiDispatcher: CoroutineDispatcher
    get() = IosCoroutineDispatcher

/**
 * iOS doesn't have a default UI thread dispatcher like [Dispatchers.Main], so we have to implement it ourself using
 * [NSRunLoop].
 */
private object IosCoroutineDispatcher : CoroutineDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        NSRunLoop.mainRunLoop().performBlock {
            block.run()
        }
    }
}