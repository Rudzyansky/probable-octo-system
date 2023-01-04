package ru.ft.giphyapp.data.service

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsChannel
import ru.ft.giphyapp.di.GiphyMediaHttpClient
import javax.inject.Inject

class GiphyMediaApi @Inject constructor(
    @GiphyMediaHttpClient
    private val client: HttpClient
) {
    suspend fun raw(url: String) = client.get(url).bodyAsChannel()
}