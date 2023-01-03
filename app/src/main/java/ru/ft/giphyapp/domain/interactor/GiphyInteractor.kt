package ru.ft.giphyapp.domain.interactor

import android.util.Log
import ru.ft.giphyapp.util.Tags
import ru.ft.giphyapp.domain.entity.GifObject
import ru.ft.giphyapp.domain.entity.GiphyRating
import ru.ft.giphyapp.domain.repository.GifRepository
import javax.inject.Inject

class GiphyInteractorImpl @Inject constructor(
    private val gifRepository: GifRepository
) : GiphyInteractor {
    override suspend fun getTrending(page: Int): List<GifObject> {
        val trending = gifRepository.getTrending(page, GiphyRating.G)
        Log.d(Tags.GiphyInteractor, trending.toString())
        return trending.gifs
    }
}

interface GiphyInteractor {

    suspend fun getTrending(page: Int): List<GifObject>
}
