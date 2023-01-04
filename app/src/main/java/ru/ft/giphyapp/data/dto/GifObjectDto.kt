package ru.ft.giphyapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GifObjectDto(
    val id: String,
    val url: String,
    val images: ImagesDto,
)