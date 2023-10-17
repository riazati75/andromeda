package ir.farsroidx.m31.dispatcher

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

fun coroutineExceptionHandler(
    block: (CoroutineContext, Throwable) -> Unit
): CoroutineExceptionHandler = CoroutineExceptionHandler { context, exception ->
    block(context, exception)
}

