package ir.farsroidx.m31

internal interface AndromedaViewState <VS: Any> {

    fun viewStateHandler(viewState: VS)

    fun getCoreViewStateViewModel(): AndromedaViewStateViewModel<VS>

}