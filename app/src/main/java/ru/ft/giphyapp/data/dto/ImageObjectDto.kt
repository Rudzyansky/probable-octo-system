package ru.ft.giphyapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageObjectDto(
    val url: String,
    val width: String,
    val height: String,
    val size: String,
    val webp: String,
    @SerialName("webp_size") val webpSize: String,
)
