package ru.ft.giphyapp.domain.interactor

import android.util.Log
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.ft.giphyapp.domain.entity.GifListObject
import ru.ft.giphyapp.domain.entity.GifObject
import ru.ft.giphyapp.domain.entity.GiphyRating
import ru.ft.giphyapp.domain.entity.LoadedContent
import ru.ft.giphyapp.domain.repository.GifRepository
import ru.ft.giphyapp.util.Tags
import javax.inject.Inject

class GiphyInteractorImpl @Inject constructor(
    private val gifRepository: GifRepository
) : GiphyInteractor {

    private val mutex = Mutex()

    override suspend fun getTrending(page: Int): GifListObject {
        val trending = gifRepository.getTrending(page, GiphyRating.G)
        Log.d(Tags.GiphyInteractor, trending.toString())
        return trending
    }

    override suspend fun loadGif(gifObject: GifObject): LoadedContent = mutex.withLock {
        gifRepository.getGif(gifObject)
    }
}

interface GiphyInteractor {

    suspend fun getTrending(page: Int): GifListObject

    suspend fun loadGif(gifObject: GifObject): LoadedContent
}
