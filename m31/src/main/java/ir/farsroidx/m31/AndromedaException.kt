package ir.farsroidx.m31

internal class AndromedaException : RuntimeException {

    constructor(message: String)
        : super(message)

    constructor(cause: Throwable)
        : super(cause)

}