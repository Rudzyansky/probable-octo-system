package ru.ft.giphyapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationObjectDto(
    @SerialName("offset") val offset: Int,
    @SerialName("total_count") val totalCount: Int,
    @SerialName("count") val count: Int,
)