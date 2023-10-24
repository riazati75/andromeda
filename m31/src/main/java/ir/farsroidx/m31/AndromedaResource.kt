package ir.farsroidx.m31

sealed class AndromedaResource<T> {

    data class Success<D>(val data: D) : AndromedaResource<D>()

    data class Failure<D>(val throwable: Throwable) : AndromedaResource<D>()

}