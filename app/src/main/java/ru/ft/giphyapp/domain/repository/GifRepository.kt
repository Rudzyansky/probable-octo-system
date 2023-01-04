package ru.ft.giphyapp.domain.repository

import ru.ft.giphyapp.domain.entity.GifListObject
import ru.ft.giphyapp.domain.entity.GifObject
import ru.ft.giphyapp.domain.entity.GiphyRating

interface GifRepository {

    suspend fun getTrending(page: Int, rating: GiphyRating): GifListObject

    suspend fun getGif(gifObject: GifObject): ByteArray
}