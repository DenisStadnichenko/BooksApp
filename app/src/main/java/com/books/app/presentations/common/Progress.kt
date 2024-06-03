package com.books.app.presentations.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.books.app.ui.theme.BooksAppTheme
import com.books.app.ui.theme.White
import com.books.app.ui.theme.WhiteAlpha20

@Composable
fun Progress(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LinearProgressIndicator(
            color = White,
            trackColor = WhiteAlpha20,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp)
                .height(6.dp)
                .clip(RoundedCornerShape(6.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressPreview() {
    BooksAppTheme {
        Progress()
    }
}
