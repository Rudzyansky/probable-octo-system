package ru.ft.giphyapp.domain.interactor

import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ru.ft.giphyapp.domain.entity.GifListObject
import ru.ft.giphyapp.domain.entity.GifObject
import ru.ft.giphyapp.domain.entity.GiphyRating
import ru.ft.giphyapp.domain.repository.GifRepository
import ru.ft.giphyapp.util.Tags
import javax.inject.Inject

class GiphyInteractorImpl @Inject constructor(
    private val gifRepository: GifRepository
) : GiphyInteractor {

    private val queue = mutableListOf<Job>()

    override suspend fun getTrending(page: Int): GifListObject {
        val trending = gifRepository.getTrending(page, GiphyRating.G)
        Log.d(Tags.GiphyInteractor, trending.toString())
        return trending
    }

    override suspend fun loadGif(gifObject: GifObject): ByteArray {
        queue.forEach { it.join() }

        lateinit var result: ByteArray
        val job = coroutineScope { launch { result = gifRepository.getGif(gifObject) } }
        queue.add(job)
        job.join()
        queue.remove(job)

        return result
    }
}

interface GiphyInteractor {

    suspend fun getTrending(page: Int): GifListObject

    suspend fun loadGif(gifObject: GifObject): ByteArray
}
