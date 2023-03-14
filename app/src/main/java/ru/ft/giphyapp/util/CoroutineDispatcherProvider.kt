package ru.ft.giphyapp.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@Suppress("PropertyName")
interface CoroutineDispatcherProvider {
    val Default: CoroutineDispatcher
    val Main: CoroutineDispatcher
    val Unconfined: CoroutineDispatcher
    val IO: CoroutineDispatcher
}

class CoroutineDispatcherProviderImpl @Inject constructor() : CoroutineDispatcherProvider {
    override val Default get() = Dispatchers.Default
    override val Main get() = Dispatchers.Main
    override val Unconfined get() = Dispatchers.Unconfined
    override val IO get() = Dispatchers.IO
}