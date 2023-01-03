package ru.ft.giphyapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import ru.ft.giphyapp.data.service.GiphyHttpClientFactory
import ru.ft.giphyapp.data.service.GiphyHttpClientFactoryImpl
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [NetworkModuleBinds::class])
class NetworkModule {

    @Provides
    @Singleton
    @GiphyHttpClient
    fun provideGiphyHttpClient(factory: GiphyHttpClientFactory): HttpClient = factory.newInstance()
}

@Module
interface NetworkModuleBinds {

    @Binds
    @Singleton
    fun bindGiphyHttpClientFactory(impl: GiphyHttpClientFactoryImpl): GiphyHttpClientFactory
}

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class GiphyHttpClient