package ru.ft.giphyapp.di

import dagger.Binds
import dagger.Module
import ru.ft.giphyapp.util.CoroutineDispatcherProvider
import javax.inject.Singleton

@Module
interface CoroutineDispatcherModule {

    @Binds
    @Singleton
    fun bindCoroutineDispatcherProvider(impl: CoroutineDispatcherProvider): CoroutineDispatcherProvider
}