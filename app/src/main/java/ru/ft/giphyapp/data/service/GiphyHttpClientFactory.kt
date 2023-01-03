package ru.ft.giphyapp.data.service

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.ft.giphyapp.util.Constants
import javax.inject.Inject

class GiphyHttpClientFactoryImpl @Inject constructor() : GiphyHttpClientFactory {
    override fun newInstance() = HttpClient(CIO) {
        val json = Json {
            ignoreUnknownKeys = true
        }
        install(ContentNegotiation) {
            json(json)
        }
        install(Logging) {
            level = LogLevel.BODY // TODO: Not logging
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.giphy.com"
                parameters.append("api_key", Constants.GIPHY_API_KEY)

                // An ID/proxy for a specific user.
                parameters.append("random_id", "todo smth") // todo random value
            }
        }
    }
}

interface GiphyHttpClientFactory {
    fun newInstance(): HttpClient
}