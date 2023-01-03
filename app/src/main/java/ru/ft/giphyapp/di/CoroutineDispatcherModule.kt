package ru.ft.giphyapp.di

import dagger.Binds
import dagger.Module
import ru.ft.giphyapp.util.CoroutineDispatcherProvider
import ru.ft.giphyapp.util.DefaultCoroutineDispatcherProvider
import ru.ft.giphyapp.util.IOCoroutineDispatcherProvider
import ru.ft.giphyapp.util.MainCoroutineDispatcherProvider
import ru.ft.giphyapp.util.UnconfinedCoroutineDispatcherProvider
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
interface CoroutineDispatcherModule {

    @Binds
    @Singleton
    @DefaultCoroutineDispatcher
    fun bindDefaultCoroutineDispatcherProvider(impl: DefaultCoroutineDispatcherProvider): CoroutineDispatcherProvider

    @Binds
    @Singleton
    @MainCoroutineDispatcher
    fun bindMainCoroutineDispatcherProvider(impl: MainCoroutineDispatcherProvider): CoroutineDispatcherProvider

    @Binds
    @Singleton
    @UnconfinedCoroutineDispatcher
    fun bindUnconfinedCoroutineDispatcherProvider(impl: UnconfinedCoroutineDispatcherProvider): CoroutineDispatcherProvider

    @Binds
    @Singleton
    @IOCoroutineDispatcher
    fun bindIOCoroutineDispatcherProvider(impl: IOCoroutineDispatcherProvider): CoroutineDispatcherProvider
}

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class DefaultCoroutineDispatcher

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class MainCoroutineDispatcher

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class UnconfinedCoroutineDispatcher

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class IOCoroutineDispatcher