package ru.ft.giphyapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GifObjectDto(
    @SerialName("id") val id: String,
    @SerialName("url") val url: String,
)