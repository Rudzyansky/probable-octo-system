package ru.ft.giphyapp.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoroutineDispatcherProviderImpl @Inject constructor() : CoroutineDispatcherProvider {
    override fun get(): CoroutineDispatcher = Dispatchers.Default
}

interface CoroutineDispatcherProvider {
    fun get(): CoroutineDispatcher
}