package ru.ft.giphyapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ft.giphyapp.Tags
import ru.ft.giphyapp.domain.interactor.GiphyInteractor
import javax.inject.Inject
import javax.inject.Provider

class HomeViewModel @Inject constructor(
    private val giphyInteractor: GiphyInteractor
) : ViewModel() {

    val a = ""

    init {
        Log.d(Tags.AndroidLifecycle, "ViewModel init")
    }

    override fun onCleared() {
        Log.d(Tags.AndroidLifecycle, "ViewModel onCleared")
        super.onCleared()
        giphyInteractor.cleanup()
    }

    class Factory @Inject constructor(
        private val homeViewModelProvider: Provider<HomeViewModel>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return homeViewModelProvider.get() as T
        }
    }
}