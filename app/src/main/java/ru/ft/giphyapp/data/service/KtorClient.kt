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
import ru.ft.giphyapp.Constants

object KtorClient {
    fun newInstance() = HttpClient(CIO) {
        val json = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
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
//                appendPathSegments("v1", "gifs") // TODO: Child replaces last segment
                parameters.append("api_key", Constants.GIPHY_API_KEY)
                parameters.append("rating", Constants.GIPHY_RATING)
                parameters.append("random_id", "random")
            }
        }
    }
}