package com.books.app.presentations.screens.splash

import com.books.app.presentations.base.ViewEvent
import com.books.app.presentations.base.ViewSideEffect
import com.books.app.presentations.base.ViewState

class SplashContract {

    sealed class Event : ViewEvent {
        data object OpenMain : Event()
    }

    sealed class State : ViewState {
        data object Loading : State()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object OpenMain : Navigation()
        }
    }
}