package com.books.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.books.app.presentations.navigation.AppNavigation
import com.books.app.ui.theme.BooksAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BooksAppTheme(darkTheme = false) {
                AppNavigation()
            }
        }
    }
}
