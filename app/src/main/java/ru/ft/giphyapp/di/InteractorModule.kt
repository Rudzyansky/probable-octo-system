package ru.ft.giphyapp.di

import dagger.Binds
import dagger.Module
import ru.ft.giphyapp.domain.interactor.GiphyInteractor
import ru.ft.giphyapp.domain.interactor.GiphyInteractorImpl
import javax.inject.Singleton

@Module
interface InteractorModule {

    @Binds
    @Singleton
    fun bindGiphyInteractor(impl: GiphyInteractorImpl): GiphyInteractor
}