package ru.ft.giphyapp.domain.entity

data class GifListObject(
    val gifs: List<GifObject>,
    val page: Int,
    val pages: Int,
)