package ru.ft.giphyapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.ft.giphyapp.domain.entity.GifListObject
import ru.ft.giphyapp.domain.entity.GifObject
import ru.ft.giphyapp.domain.interactor.GiphyInteractor
import ru.ft.giphyapp.ui.util.BaseViewModel
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Provider

class HomeViewModel @Inject constructor(
    private val giphyInteractor: GiphyInteractor
) : BaseViewModel() {

    private val _currentList = MutableSharedFlow<GifListObject>(1, 0, BufferOverflow.DROP_OLDEST)

    val currentList get() = _currentList.asSharedFlow()

    init {
        viewModelScope.launch {
            _currentList.emit(giphyInteractor.getTrending(0))
        }
    }

    fun nextPage() {
        viewModelScope.launch {
            val list = currentList.first()
            val nextPage = list.currentPage + 1
            if (nextPage < list.pagesCount)
                _currentList.emit(giphyInteractor.getTrending(nextPage))
        }
    }

    suspend fun load(gifObject: GifObject): ByteArray {
        return giphyInteractor.loadGif(gifObject)
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