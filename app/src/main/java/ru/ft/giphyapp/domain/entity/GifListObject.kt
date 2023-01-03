package ru.ft.giphyapp.domain.entity

data class GifListObject(
    val gifs: List<GifObject>,
    val currentPage: Int,
    val pagesCount: Int,
)