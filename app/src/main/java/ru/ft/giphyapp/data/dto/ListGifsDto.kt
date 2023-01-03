package ru.ft.giphyapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ListGifsDto(
    val data: List<GifObjectDto>,
    val pagination: PaginationObjectDto,
    val meta: MetaObjectDto
)