package com.books.app.presentations.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.books.app.R
import com.books.app.presentations.base.SIDE_EFFECTS_KEY
import com.books.app.presentations.common.Progress
import com.books.app.presentations.screens.splash.composable.SplashBackground
import com.books.app.ui.theme.SplashMainText
import com.books.app.ui.theme.WhiteAlpha80
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SplashScreen(
    state: SplashContract.State,
    effectFlow: Flow<SplashContract.Effect>?,
    onEventSent: (event: SplashContract.Event) -> Unit,
    onNavigationRequested: (SplashContract.Effect.Navigation) -> Unit
) {

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                SplashContract.Effect.Navigation.OpenMain -> {
                    onNavigationRequested(SplashContract.Effect.Navigation.OpenMain)
                }
            }
        }?.collect()
    }

    LaunchedEffect(Unit) {
        delay(3000)
        val intent = SplashContract.Event.OpenMain
        onEventSent.invoke(intent)
    }

    Box {
        SplashBackground()
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.book_app),
                style = TextStyle(
                    color = SplashMainText,
                    fontSize = 52.sp,
                    lineHeight = 52.sp,
                    fontFamily = FontFamily(
                        Font(R.font.georgia_bold_italic)
                    )
                )
            )
            Text(
                text = stringResource(R.string.welcome_to_book_app),
                color = WhiteAlpha80,
                fontSize = 24.sp,
                fontFamily = FontFamily(
                    Font(R.font.nunito_sans_bold)
                ),
                style = TextStyle(
                    color = WhiteAlpha80,
                    fontSize = 24.sp,
                    lineHeight = 26.4.sp,
                    fontFamily = FontFamily(
                        Font(R.font.nunito_sans_regular)
                    )
                ),
                modifier = Modifier
                    .height(52.dp)
                    .padding(top = 12.dp)
            )
            when (state) {
                SplashContract.State.Loading -> Progress(
                    modifier = Modifier.padding(top = 24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun SplashScreenLoadingPreview() {
    SplashScreen(
        state = SplashContract.State.Loading,
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}