package ru.ft.giphyapp.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import ru.ft.giphyapp.data.dto.ListGifsDto
import ru.ft.giphyapp.di.GiphyHttpClient
import javax.inject.Inject

class GiphyApi @Inject constructor(
    @GiphyHttpClient
    private val client: HttpClient
) {
    suspend fun trending(offset: Int, limit: Int, rating: String) = client.get {
        url.apply {
            appendPathSegments("v1", "gifs", "trending")
            parameters.append("limit", limit.toString())
            parameters.append("offset", offset.toString())
            parameters.append("rating", rating)

            // https://developers.giphy.com/docs/optional-settings/#renditions-on-demand
            parameters.append("bundle", "messaging_non_clips")
        }
    }.body<ListGifsDto>()
}