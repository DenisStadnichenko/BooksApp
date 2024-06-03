package com.books.app.domain.usecase

import com.books.app.data.model.BooksResponseModel
import com.books.app.data.repository.BooksRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface BooksUseCase {
    fun getBooks(): Flow<BooksResponseModel>
}

class BooksUseCaseImpl(
    private val booksRepository: BooksRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BooksUseCase {

    override fun getBooks() = booksRepository.getBooks()
        .flowOn(dispatcher)

}