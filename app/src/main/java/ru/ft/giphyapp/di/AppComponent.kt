package ru.ft.giphyapp.di

import dagger.Component
import ru.ft.giphyapp.data.service.GiphyApi
import ru.ft.giphyapp.ui.home.HomeFragment
import ru.ft.giphyapp.ui.home.HomeViewModel

@Component(modules = [AppModule::class])
interface AppComponent {

    val giphyApi: GiphyApi

    val homeViewModelFactory: HomeViewModel.Factory

    fun inject(homeFragment: HomeFragment)
}