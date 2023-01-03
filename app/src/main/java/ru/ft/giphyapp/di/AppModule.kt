package ru.ft.giphyapp.di

import dagger.Module

@Module(
    includes = [
        CoroutineDispatcherModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        InteractorModule::class,
    ]
)
interface AppModule