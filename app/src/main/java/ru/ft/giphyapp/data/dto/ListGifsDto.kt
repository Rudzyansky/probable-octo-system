package ru.ft.giphyapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListGifsDto(
    @SerialName("data") val data: List<GifObjectDto>,
    @SerialName("pagination") val pagination: PaginationObjectDto,
    @SerialName("meta") val meta: MetaObjectDto
)