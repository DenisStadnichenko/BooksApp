package com.books.app.presentations.screens.main

import Group
import androidx.lifecycle.viewModelScope
import com.books.app.data.model.BooksResponseModel
import com.books.app.domain.ResultState
import com.books.app.domain.asResultState
import com.books.app.domain.usecase.BooksUseCase
import com.books.app.presentations.base.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class MainViewModel(
    private val booksUseCase: BooksUseCase
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    init {
        fetchBooks()
    }

    override fun setInitialState() = MainContract.State.Loading

    override fun handleEvents(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.ClickBook -> setEffect {
                MainContract.Effect.Navigation.OpenDetails(
                    event.bookId
                )
            }

            MainContract.Event.Exit -> setEffect {
                MainContract.Effect.Navigation.Finish
            }
        }
    }

    private fun fetchBooks() {
        booksUseCase.getBooks()
            .asResultState()
            .map(::mapToFetchScoreUiState)
            .onEach { fetchScoreState ->
                setState {
                    fetchScoreState
                }
            }.launchIn(viewModelScope)
    }

    private fun mapToFetchScoreUiState(resultState: ResultState<BooksResponseModel>) =
        when (resultState) {
            is ResultState.Loading -> {
                MainContract.State.Loading
            }

            is ResultState.Success -> {
                if (resultState.data.books.isEmpty()) {
                    MainContract.State.Empty
                } else {
                    val groups = resultState.data.books.groupBy { it.genre }
                        .map { (genre, books) -> Group(title = genre, books = books) }
                    MainContract.State.Success(books = groups, topBook = resultState.data.top)
                }
            }

            is ResultState.Error -> {
                MainContract.State.Error
            }
        }

}