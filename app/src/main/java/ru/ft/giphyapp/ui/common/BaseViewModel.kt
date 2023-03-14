package ru.ft.giphyapp.ui.common

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.ft.giphyapp.util.Tags

abstract class BaseViewModel : ViewModel() {

    init {
        Log.d(Tags.AndroidLifecycle, "ViewModel ${javaClass.canonicalName} init")
    }

    override fun onCleared() {
        Log.d(Tags.AndroidLifecycle, "ViewModel ${javaClass.canonicalName} onCleared")
        super.onCleared()
    }
}