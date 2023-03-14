package ru.ft.giphyapp.ui.common

sealed interface ScreenState {

    object Loading : ScreenState

    class Content(var content: List<DisplayContent>) : ScreenState

    class Error(val message: String) : ScreenState
}