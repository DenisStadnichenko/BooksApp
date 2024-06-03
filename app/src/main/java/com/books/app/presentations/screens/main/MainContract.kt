package com.books.app.presentations.screens.main

import Group
import com.books.app.data.model.TopBook
import com.books.app.presentations.base.ViewEvent
import com.books.app.presentations.base.ViewSideEffect
import com.books.app.presentations.base.ViewState

class MainContract {

    sealed class Event : ViewEvent {
        data class ClickBook(val bookId: Int) : Event()
        data object Exit : Event()
    }

    sealed class State : ViewState {
        data object Loading : State()
        data object Empty : State()
        data object Error : State()
        data class Success(
            val topBook: List<TopBook>,
            val books: List<Group>
        ) : State()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object Finish : Navigation()
            data class OpenDetails(val bookId: Int) : Navigation()
        }
    }
}