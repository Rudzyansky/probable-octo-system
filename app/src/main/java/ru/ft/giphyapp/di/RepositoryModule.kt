package ru.ft.giphyapp.di

import dagger.Binds
import dagger.Module
import ru.ft.giphyapp.data.implementation.GifRepositoryImpl
import ru.ft.giphyapp.domain.repository.GifRepository
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindGifRepository(impl: GifRepositoryImpl): GifRepository
}