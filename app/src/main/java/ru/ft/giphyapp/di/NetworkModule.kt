package ru.ft.giphyapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import ru.ft.giphyapp.data.service.GiphyHttpClientFactory
import ru.ft.giphyapp.data.service.GiphyHttpClientFactoryImpl
import ru.ft.giphyapp.data.service.GiphyMediaHttpClientFactory
import ru.ft.giphyapp.data.service.GiphyMediaHttpClientFactoryImpl
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
interface NetworkModule {

    @Binds
    @Singleton
    fun bindGiphyHttpClientFactory(impl: GiphyHttpClientFactoryImpl): GiphyHttpClientFactory

    @Binds
    @Singleton
    fun bindGiphyMediaHttpClientFactory(impl: GiphyMediaHttpClientFactoryImpl): GiphyMediaHttpClientFactory

    companion object {

        @Provides
        @Singleton
        @GiphyHttpClient
        fun provideGiphyHttpClient(factory: GiphyHttpClientFactory): HttpClient = factory.newInstance()

        @Provides
        @Singleton
        @GiphyMediaHttpClient
        fun provideGiphyMediaHttpClient(factory: GiphyMediaHttpClientFactory): HttpClient = factory.newInstance()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GiphyHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GiphyMediaHttpClient
