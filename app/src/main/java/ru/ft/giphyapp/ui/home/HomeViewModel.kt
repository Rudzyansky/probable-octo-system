package ru.ft.giphyapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ft.giphyapp.domain.interactor.GiphyInteractor
import ru.ft.giphyapp.ui.util.BaseViewModel
import javax.inject.Inject
import javax.inject.Provider

class HomeViewModel @Inject constructor(
    private val giphyInteractor: GiphyInteractor
) : BaseViewModel() {

    val a = ""

    init {
        viewModelScope.launch {
            giphyInteractor.getTrending(0)
        }
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