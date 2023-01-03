package ru.ft.giphyapp.data.service

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import ru.ft.giphyapp.Constants
import javax.inject.Inject

class GiphyApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun trending(offset: Int) = client.get {
        url.apply {
            appendPathSegments("v1", "gifs", "trending")
            parameters.append("limit", Constants.GIPHY_LIMIT.toString())
            parameters.append("offset", offset.toString())
            parameters.append("bundle", "messaging_non_clips")
        }
    }
}