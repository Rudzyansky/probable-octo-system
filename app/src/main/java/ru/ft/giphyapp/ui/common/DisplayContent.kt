package ru.ft.giphyapp.ui.common

import ru.ft.giphyapp.domain.entity.GifObject
import ru.ft.giphyapp.domain.entity.LoadedContent

sealed interface DisplayContent {

    data class Loading(val gifObject: GifObject) : DisplayContent

    data class Gif(val gifObject: GifObject, val loadedContent: LoadedContent) : DisplayContent

    class Error(val message: String) : DisplayContent
}