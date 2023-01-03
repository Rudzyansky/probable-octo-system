package ru.ft.giphyapp.data

import ru.ft.giphyapp.Constants
import ru.ft.giphyapp.data.dto.GifObjectDto
import ru.ft.giphyapp.data.dto.ListGifsDto
import ru.ft.giphyapp.domain.entity.GifListObject
import ru.ft.giphyapp.domain.entity.GifObject

val GifObjectDto.domain get() = GifObject(id = id, url = url)

val ListGifsDto.domain
    get() = GifListObject(
        gifs = data.map { it.domain },
        page = pagination.offset / Constants.GIPHY_LIMIT,
        pages = pagination.totalCount / Constants.GIPHY_LIMIT, // TODO: Math.ceil()
    )