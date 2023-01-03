package ru.ft.giphyapp.data.implementation

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import ru.ft.giphyapp.Constants
import ru.ft.giphyapp.Tags
import ru.ft.giphyapp.data.domain
import ru.ft.giphyapp.data.dto.ListGifsDto
import ru.ft.giphyapp.data.service.GiphyApi
import ru.ft.giphyapp.domain.entity.GifListObject
import ru.ft.giphyapp.domain.repository.GifRepository
import javax.inject.Inject

class GifRepositoryImpl @Inject constructor(
    private val api: GiphyApi
) : GifRepository {

    override suspend fun getTrending(page: Int): GifListObject {
        return api.trending(page * Constants.GIPHY_LIMIT).apply {
            Log.d(Tags.KtorClient, this.request.url.encodedPath)
            Log.d(Tags.KtorClient, bodyAsText())
        }.body<ListGifsDto>().domain
    }
}