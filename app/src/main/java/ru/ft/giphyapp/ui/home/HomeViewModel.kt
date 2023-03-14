package ru.ft.giphyapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ft.giphyapp.domain.entity.GifObject
import ru.ft.giphyapp.domain.entity.LoadedContent
import ru.ft.giphyapp.domain.interactor.GiphyInteractor
import ru.ft.giphyapp.ui.common.DisplayContent
import ru.ft.giphyapp.ui.common.ScreenState
import ru.ft.giphyapp.ui.common.BaseViewModel
import javax.inject.Inject
import javax.inject.Provider

class HomeViewModel @Inject constructor(
    private val giphyInteractor: GiphyInteractor
) : BaseViewModel() {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)

    val screenState get() = _screenState.asStateFlow()

    private var currentPage = 0
    private var totalPages = 0

    init {
        viewModelScope.launch {
            val state = try {
                val trending = giphyInteractor.getTrending(0)
                totalPages = trending.pagesCount
                ScreenState.Content(trending.gifs.map { DisplayContent.Gif(it) })
            } catch (e: Throwable) {
                ScreenState.Error(e.localizedMessage ?: "Unknown Error")
            }
            _screenState.emit(state)
        }
    }

    fun nextPage() {
        viewModelScope.launch {
            val nextPage = currentPage + 1
            if (nextPage < totalPages) {
//                _currentList.emit(giphyInteractor.getTrending(nextPage))
                currentPage = nextPage
            }
        }
    }

    suspend fun load(gifObject: GifObject): LoadedContent {
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