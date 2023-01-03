package ru.ft.giphyapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationObjectDto(
    val offset: Int,
    @SerialName("total_count") val totalCount: Int,
    val count: Int,
)