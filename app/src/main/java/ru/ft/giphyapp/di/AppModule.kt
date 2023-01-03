package ru.ft.giphyapp.di

import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        RepositoryModule::class,
        InteractorModule::class,
        CoroutineDispatcherModule::class,
    ]
)
interface AppModule