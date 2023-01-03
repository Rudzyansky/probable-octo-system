package ru.ft.giphyapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaObjectDto(
    @SerialName("msg") val msg: String,
    @SerialName("status") val status: Int,
    @SerialName("response_id") val responseId: String,
)