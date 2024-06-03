package com.books.app.presentations.screens.details

import com.books.app.data.model.BookResponseModel
import com.books.app.presentations.base.ViewEvent
import com.books.app.presentations.base.ViewSideEffect
import com.books.app.presentations.base.ViewState

class DetailsContract {

    sealed class Event : ViewEvent {
        data class FetchBook(val bookId: Int) : Event()
        data class ReadBook(val bookId: Int) : Event()

        //  data class DetailsBook(val book: BookResponseModel) : Event()
        data object BackButtonClicked : Event()
    }

    sealed class State : ViewState {
        data object Loading : State()
        data class Success(
            val book: BookResponseModel,
            val books: List<BookResponseModel>,
            val likes: List<BookResponseModel>
        ) : State()

        data object Error : State()
        data object Empty : State()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object Back : Navigation()
            data class Read(val bookId: Int) : Navigation()
            data class Details(val bookId: Int) : Navigation()
        }
    }
}