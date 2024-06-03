package com.books.app.presentations.screens.details

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.books.app.data.model.BookResponseModel
import com.books.app.data.model.BooksResponseModel
import com.books.app.domain.ResultState
import com.books.app.domain.asResultState
import com.books.app.domain.usecase.BooksUseCase
import com.books.app.presentations.base.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class DetailsViewModel(
    val booksUseCase: BooksUseCase
) :
    BaseViewModel<DetailsContract.Event, DetailsContract.State, DetailsContract.Effect>() {


    override fun setInitialState() = DetailsContract.State.Loading

    override fun handleEvents(event: DetailsContract.Event) {
        when (event) {
            DetailsContract.Event.BackButtonClicked -> {
                setEffect { DetailsContract.Effect.Navigation.Back }
            }

            is DetailsContract.Event.FetchBook -> fetchBooks(event.bookId)

            is DetailsContract.Event.ReadBook -> setEffect {
                DetailsContract.Effect.Navigation.Read(
                    event.bookId
                )
            }
        }
    }

    private fun fetchBooks(bookId: Int) {
        booksUseCase.getBooks()
            .asResultState()
            .map { mapToFetchScoreUiState(it, bookId) }
            .onEach { fetchScoreState ->
                setState { fetchScoreState }
            }.launchIn(viewModelScope)
    }

    private fun mapToFetchScoreUiState(
        resultState: ResultState<BooksResponseModel>,
        bookId: Int
    ) =
        when (resultState) {
            is ResultState.Loading -> DetailsContract.State.Loading
            is ResultState.Error -> DetailsContract.State.Error
            is ResultState.Success -> {
                val books = resultState.data.books
                if (books.isEmpty()) {
                    DetailsContract.State.Empty
                } else {
                    val book = books.find { it.id == bookId }
                    if (book == null) {
                        Log.e("TAG", "Book is null")
                        DetailsContract.State.Error
                    } else {
                        val ids = resultState.data.like
                        val likes = filterBooksByIds(ids, books)
                        DetailsContract.State.Success(
                            books = books,
                            book = book,
                            likes = likes
                        )
                    }
                }
            }
        }

    private fun filterBooksByIds(
        bookIds: List<Int>,
        books: List<BookResponseModel>
    ): List<BookResponseModel> {
        return books.filter { book -> book.id in bookIds }
    }
}