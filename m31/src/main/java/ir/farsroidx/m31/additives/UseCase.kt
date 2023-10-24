package ir.farsroidx.m31.additives

import ir.farsroidx.m31.AndromedaResource
import kotlinx.coroutines.flow.FlowCollector

suspend fun <T: Any> FlowCollector<AndromedaResource<T>>.emitResource(callback: suspend () -> T) {
    emit(getResource(callback))
}

suspend fun <T: Any> getResource(callback: suspend () -> T): AndromedaResource<T> {
    return try {
        AndromedaResource.Success( callback() )
    } catch (throwable: Throwable) {
        AndromedaResource.Failure( throwable )
    }
}