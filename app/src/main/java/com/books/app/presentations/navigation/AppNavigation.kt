package com.books.app.presentations.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.SPLASH
    ) {
        splash(navController)
        main(navController)
        details(navController)
    }
}

object Navigation {
    object Args {
        const val BOOK_ID = "book_id"
    }

    object Routes {
        const val SPLASH = "splash"
        const val MAIN = "main"
        const val DETAILS = "details/{${Args.BOOK_ID}}"
    }
}
