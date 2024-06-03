package com.books.app.presentations.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.books.app.MainActivity
import com.books.app.presentations.common.enterTransition
import com.books.app.presentations.common.exitTransition
import com.books.app.presentations.common.popEnterTransition
import com.books.app.presentations.common.popExitTransition
import com.books.app.presentations.screens.main.MainContract
import com.books.app.presentations.screens.main.MainScreen
import com.books.app.presentations.screens.main.MainViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainScreenDestination(
    navController: NavController,
    viewModel: MainViewModel = koinViewModel()
) {
    val activity = LocalContext.current as MainActivity

    MainScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is MainContract.Effect.Navigation.OpenDetails -> navController.navigateToDetails(
                    navigationEffect.bookId
                )

                MainContract.Effect.Navigation.Finish -> {
                    activity.finish()
                }
            }
        }
    )
}

fun NavController.navigateToMain() {
    navigate(route = Navigation.Routes.MAIN)
}

fun NavGraphBuilder.main(
    navController: NavController
) {
    composable(
        route = Navigation.Routes.MAIN,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() }
    ) {
        MainScreenDestination(navController)
    }
}