package ru.ft.giphyapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaObjectDto(
    val msg: String,
    val status: Int,
    @SerialName("response_id") val responseId: String,
)