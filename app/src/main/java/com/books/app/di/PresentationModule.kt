package com.books.app.di

import com.books.app.presentations.screens.details.DetailsViewModel
import com.books.app.presentations.screens.main.MainViewModel
import com.books.app.presentations.screens.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { SplashViewModel() }
    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}