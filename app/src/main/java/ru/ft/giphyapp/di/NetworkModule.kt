package ru.ft.giphyapp.di

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import ru.ft.giphyapp.data.service.KtorClient
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
//    @Singleton
    fun provideKtorClient(): HttpClient = KtorClient.newInstance()
}