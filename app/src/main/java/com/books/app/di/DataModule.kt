package com.books.app.di

import com.books.app.data.api.BookServiceImp
import com.books.app.data.api.BooksService
import com.books.app.data.repository.BooksRepository
import com.books.app.data.repository.BooksRepositoryImp
import org.koin.dsl.module

val dataModule = module {

    single<BooksService> {
        BookServiceImp()
    }

    single<BooksRepository> {
        BooksRepositoryImp(get())
    }
}