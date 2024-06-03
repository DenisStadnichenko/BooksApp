package com.books.app.data.repository

import com.books.app.data.api.BooksService
import com.books.app.data.model.BooksResponseModel
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    fun getBooks(): Flow<BooksResponseModel>
}

class BooksRepositoryImp(
    private val booksService: BooksService
) : BooksRepository {
    override fun getBooks() = booksService.getBooks()
}