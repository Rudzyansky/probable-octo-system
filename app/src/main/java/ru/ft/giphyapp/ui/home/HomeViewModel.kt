package ru.ft.giphyapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class HomeViewModel(
) : ViewModel() {

    val a = ""

    init {
        Log.d("AAA", "ViewModel init")
    }

    override fun onCleared() {
        Log.d("AAA", "ViewModel onCleared")
        super.onCleared()
    }

    class Factory @Inject constructor(
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel() as T
        }
    }
}