package com.books.app.presentations.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NetworkError(
    modifier: Modifier = Modifier,
    onRetryButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Error",
            textAlign = TextAlign.Center,
        )

        Button(onClick = { onRetryButtonClick() }) {
            Text(
                text = "Retry"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NetworkErrorPreview() {
    NetworkError(modifier = Modifier, onRetryButtonClick = {})
}