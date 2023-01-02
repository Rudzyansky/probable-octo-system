package ru.ft.giphyapp.di

import dagger.Component
import ru.ft.giphyapp.ui.home.HomeFragment

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(homeFragment: HomeFragment)
}