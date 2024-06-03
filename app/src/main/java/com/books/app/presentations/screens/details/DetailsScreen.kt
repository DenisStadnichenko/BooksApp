package com.books.app.presentations.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.books.app.data.model.BookResponseModel
import com.books.app.presentations.base.SIDE_EFFECTS_KEY
import com.books.app.presentations.common.LoadingDialog
import com.books.app.presentations.common.WarningComponent
import com.books.app.presentations.screens.details.composables.DetailsBackground
import com.books.app.presentations.screens.details.composables.DetailsBook
import com.books.app.presentations.screens.details.composables.HorizontalPager
import com.books.app.presentations.screens.main.composables.HeaderBack
import com.books.app.ui.theme.MainBackground
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun DetailsScreen(
    bookId: Int,
    state: DetailsContract.State,
    effectFlow: Flow<DetailsContract.Effect>?,
    onEventSent: (event: DetailsContract.Event) -> Unit,
    onNavigationRequested: (DetailsContract.Effect.Navigation) -> Unit
) {

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                DetailsContract.Effect.Navigation.Back -> {
                    onNavigationRequested(DetailsContract.Effect.Navigation.Back)
                }

                is DetailsContract.Effect.Navigation.Details -> {
                    onNavigationRequested(DetailsContract.Effect.Navigation.Details(effect.bookId))
                }

                is DetailsContract.Effect.Navigation.Read -> {
                    onNavigationRequested(DetailsContract.Effect.Navigation.Read(effect.bookId))
                }
            }
        }?.collect()
    }

    LaunchedEffect(Unit) {
        val intent = DetailsContract.Event.FetchBook(bookId)
        onEventSent.invoke(intent)
    }

    Box(
        modifier = Modifier
            .background(MainBackground)
            .fillMaxSize()

    ) {
        DetailsBackground()
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderBack { onEventSent(DetailsContract.Event.BackButtonClicked) }

            val currentBook = remember { mutableStateOf<BookResponseModel?>(null) }

            when (state) {
                DetailsContract.State.Loading -> LoadingDialog(loading = true)
                DetailsContract.State.Error -> WarningComponent(
                    modifier = Modifier,
                    message = "Oops, something went wrong."
                )

                DetailsContract.State.Empty -> WarningComponent(
                    modifier = Modifier,
                    message = "Empty"
                )

                is DetailsContract.State.Success -> {
                    HorizontalPager(
                        state.book,
                        state.books,
                        onBookChanged = { currentBook.value = it }
                    )
                    DetailsBook(
                        book = currentBook.value,
                        items = state.likes,
                        onClickBookEventSent = { bookId ->
                            onEventSent(
                                DetailsContract.Event.FetchBook(
                                    bookId
                                )
                            )
                        },
                        onClickReadNiwEventSent = {
                            onEventSent(
                                DetailsContract.Event.ReadBook(
                                    bookId
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        bookId = 1,
        state = DetailsContract.State.Loading,
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}
