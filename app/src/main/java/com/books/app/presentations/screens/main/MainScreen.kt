package com.books.app.presentations.screens.main

import StickyHeaderList
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.books.app.presentations.base.SIDE_EFFECTS_KEY
import com.books.app.presentations.common.LoadingDialog
import com.books.app.presentations.common.WarningComponent
import com.books.app.presentations.screens.main.composables.HeaderText
import com.books.app.presentations.screens.main.composables.TopSlider
import com.books.app.ui.theme.MainBackground
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MainScreen(
    state: MainContract.State,
    effectFlow: Flow<MainContract.Effect>?,
    onEventSent: (event: MainContract.Event) -> Unit,
    onNavigationRequested: (MainContract.Effect.Navigation) -> Unit
) {

    BackHandler(enabled = true) {
        onEventSent(MainContract.Event.Exit)
    }

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is MainContract.Effect.Navigation.OpenDetails -> {
                    onNavigationRequested(MainContract.Effect.Navigation.OpenDetails(effect.bookId))
                }

                MainContract.Effect.Navigation.Finish -> {
                    onNavigationRequested(MainContract.Effect.Navigation.Finish)
                }
            }
        }?.collect()
    }

    Box(
        modifier = Modifier
            .background(MainBackground)
            .fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderText()

            when (state) {
                MainContract.State.Loading -> LoadingDialog(loading = true)
                MainContract.State.Empty -> WarningComponent(
                    modifier = Modifier,
                    message = "Empty"
                )

                is MainContract.State.Success -> {
                    TopSlider(state.topBook, onEventSent)
                    StickyHeaderList(
                        groups = state.books
                    ) {
                        onEventSent(MainContract.Event.ClickBook(it))
                    }
                }

                MainContract.State.Error -> WarningComponent(
                    modifier = Modifier,
                    message = "Oops, something went wrong."
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenLoadingPreview() {
    MainScreen(
        state = MainContract.State.Loading,
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}


@Preview(showBackground = true)
@Composable
fun MainScreenEmptyPreview() {
    MainScreen(
        state = MainContract.State.Empty,
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}

//@Preview(showBackground = true)
//@Composable
//fun MainScreenSuccessPreview() {
//    MainScreen(
//        state = MainContract.State.Success(listOf("1", "2", "3")),
//        effectFlow = null,
//        onEventSent = {},
//        onNavigationRequested = {},
//    )
//}

