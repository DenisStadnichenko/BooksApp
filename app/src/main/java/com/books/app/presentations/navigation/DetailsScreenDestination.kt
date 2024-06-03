package com.books.app.presentations.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.books.app.presentations.common.enterTransition
import com.books.app.presentations.common.exitTransition
import com.books.app.presentations.common.popEnterTransition
import com.books.app.presentations.common.popExitTransition
import com.books.app.presentations.screens.details.DetailsContract
import com.books.app.presentations.screens.details.DetailsScreen
import com.books.app.presentations.screens.details.DetailsViewModel
import org.koin.androidx.compose.koinViewModel

fun NavController.navigateToDetails(bookId: Int) {
    navigate(route = "details/$bookId")
}


fun NavGraphBuilder.details(
    navController: NavController
) {
    composable(
        route = Navigation.Routes.DETAILS,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() },
        arguments = listOf(navArgument(name = Navigation.Args.BOOK_ID) {
            type = NavType.IntType
        })
    ) {
        val bookId =
            requireNotNull(it.arguments?.getInt(Navigation.Args.BOOK_ID)) { "BookId required argument" }
        DetailsScreenDestination(bookId = bookId, navController = navController)
    }
}


@Composable
fun DetailsScreenDestination(
    bookId: Int,
    navController: NavController,
    viewModel: DetailsViewModel = koinViewModel()
) {
    val context = LocalContext.current

    DetailsScreen(
        bookId = bookId,
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is DetailsContract.Effect.Navigation.Details -> navController.navigateToDetails(
                    navigationEffect.bookId
                )

                DetailsContract.Effect.Navigation.Back -> navController.popBackStack()
                is DetailsContract.Effect.Navigation.Read -> {
                    Toast.makeText(context, "In Develop", Toast.LENGTH_SHORT).show()
                }
            }
        }
    )
}