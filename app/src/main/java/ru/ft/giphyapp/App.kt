package ru.ft.giphyapp

import android.app.Application
import ru.ft.giphyapp.di.AppComponent
import ru.ft.giphyapp.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}