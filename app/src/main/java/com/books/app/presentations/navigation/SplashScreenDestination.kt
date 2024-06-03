package com.books.app.presentations.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.books.app.presentations.common.enterTransition
import com.books.app.presentations.common.exitTransition
import com.books.app.presentations.common.popEnterTransition
import com.books.app.presentations.common.popExitTransition
import com.books.app.presentations.screens.splash.SplashContract
import com.books.app.presentations.screens.splash.SplashScreen
import com.books.app.presentations.screens.splash.SplashViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreenDestination(
    navController: NavController,
    viewModel: SplashViewModel = koinViewModel()
) {
    SplashScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is SplashContract.Effect.Navigation.OpenMain) {
                navController.navigateToMain()
            }
        },
    )
}

fun NavGraphBuilder.splash(
    navController: NavController
) {
    composable(
        route = Navigation.Routes.SPLASH,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() }
    ) {
        SplashScreenDestination(navController)
    }
}

