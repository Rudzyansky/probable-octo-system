package ru.ft.giphyapp.di

import dagger.Binds
import dagger.Module
import ru.ft.giphyapp.data.implementation.GifRepositoryImpl
import ru.ft.giphyapp.domain.repository.GifRepository

@Module(includes = [NetworkModule::class])
interface AppModule {

    @Binds
    fun bindGifRepository(impl: GifRepositoryImpl): GifRepository
}