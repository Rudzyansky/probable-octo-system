package ru.ft.giphyapp.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface CoroutineDispatcherProvider {
    fun get(): CoroutineDispatcher
}

class DefaultCoroutineDispatcherProvider @Inject constructor() : CoroutineDispatcherProvider {
    override fun get(): CoroutineDispatcher = Dispatchers.Default
}

class MainCoroutineDispatcherProvider @Inject constructor() : CoroutineDispatcherProvider {
    override fun get(): CoroutineDispatcher = Dispatchers.Main
}

class UnconfinedCoroutineDispatcherProvider @Inject constructor() : CoroutineDispatcherProvider {
    override fun get(): CoroutineDispatcher = Dispatchers.Unconfined
}

class IOCoroutineDispatcherProvider @Inject constructor() : CoroutineDispatcherProvider {
    override fun get(): CoroutineDispatcher = Dispatchers.IO
}