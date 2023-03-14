package ru.ft.giphyapp.data.service

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import javax.inject.Inject

class GiphyMediaHttpClientFactoryImpl @Inject constructor() : GiphyMediaHttpClientFactory {
    override fun newInstance() = HttpClient(CIO)
}

interface GiphyMediaHttpClientFactory {
    fun newInstance(): HttpClient
}