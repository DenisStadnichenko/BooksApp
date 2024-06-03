package com.books.app.presentations.screens.splash

import com.books.app.presentations.base.BaseViewModel

class SplashViewModel :
    BaseViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>() {

    override fun setInitialState() = SplashContract.State.Loading

    override fun handleEvents(event: SplashContract.Event) {
        when (event) {
            SplashContract.Event.OpenMain -> mapToOpenStartScreen()
        }
    }

    private fun mapToOpenStartScreen() {
        setEffect {
            SplashContract.Effect.Navigation.OpenMain
        }
    }
}