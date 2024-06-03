package com.books.app

import android.app.Application
import com.books.app.di.appModules
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BooksApp : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BooksApp)
            modules(appModules)
        }
    }
}