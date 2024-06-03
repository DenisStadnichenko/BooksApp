package com.books.app.di

import com.books.app.domain.usecase.BooksUseCase
import com.books.app.domain.usecase.BooksUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory<BooksUseCase> {
        BooksUseCaseImpl(
            booksRepository = get()
        )
    }
}