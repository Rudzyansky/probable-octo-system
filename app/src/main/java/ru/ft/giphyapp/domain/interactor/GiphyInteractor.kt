package ru.ft.giphyapp.domain.interactor

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.ft.giphyapp.Tags
import ru.ft.giphyapp.domain.repository.GifRepository
import javax.inject.Inject

class GiphyInteractor @Inject constructor(
    private val gifRepository: GifRepository
) {
    private val scope = CoroutineScope(Dispatchers.IO)

    private var page = 0
    private var pages = 0

    init {
        scope.launch {
            val a = gifRepository.getTrending(0)
                .also { page = it.page }
                .also { pages = it.pages }
                .gifs
            Log.d(Tags.GiphyInteractor, a.toString())
        }
    }

    fun cleanup() {
        scope.cancel()
    }
}