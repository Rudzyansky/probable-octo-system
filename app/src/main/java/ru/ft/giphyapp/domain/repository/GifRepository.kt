package ru.ft.giphyapp.domain.repository

import ru.ft.giphyapp.domain.entity.GifListObject

interface GifRepository {

    suspend fun getTrending(page: Int): GifListObject
}