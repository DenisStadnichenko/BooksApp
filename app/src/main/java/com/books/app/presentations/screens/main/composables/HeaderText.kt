package com.books.app.presentations.screens.main.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.books.app.R
import com.books.app.ui.theme.MainTextColor


@Composable
fun HeaderText() {
    Text(
        text = "Library",
        color = MainTextColor,
        fontSize = 22.sp,
        fontFamily = FontFamily(
            Font(R.font.nunito_sans_bold)
        ),
        modifier = Modifier
            .padding(start = 16.dp, top = 18.dp, bottom = 8.dp)
            .fillMaxWidth()
    )
}

@Composable
fun HeaderBack(
    onEventSent: () -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable { onEventSent.invoke() }) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = "",
            modifier = Modifier.padding(start = 17.dp, top = 26.dp)
        )
    }
}


@Preview(showBackground = false)
@Composable
fun HeaderListPreview() {
    HeaderText()
}


@Preview(showBackground = false)
@Composable
fun BackButtonPreview() {
    HeaderBack() {}
}

