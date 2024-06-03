package com.books.app.presentations.screens.details.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.books.app.R
import com.books.app.data.model.BookResponseModel
import com.books.app.ui.theme.ListTextInfo
import com.books.app.ui.theme.MainTextInfo
import com.books.app.ui.theme.PreviewImage
import com.books.app.ui.theme.ReadNowBtnColor
import com.books.app.ui.theme.SpacerInfo
import com.books.app.ui.theme.SummaryInfo
import com.books.app.ui.theme.TextInfo
import com.books.app.ui.theme.White

@Composable
fun DetailsBook(
    book: BookResponseModel?,
    items: List<BookResponseModel>,
    onClickBookEventSent: (bookId: Int) -> Unit,
    onClickReadNiwEventSent: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(White)
            .verticalScroll(rememberScrollState())

    ) {
        Info(book)
        SpacerInfo()
        InfoTitle(stringResource(R.string.summary))
        SummaryText(book?.summary ?: "")
        SpacerInfo()
        InfoTitle(stringResource(R.string.you_will_also_like))
        HorizontalItems(items, onClickBookEventSent)
        ReadNowButton(onClickReadNiwEventSent)
    }
}


@Preview(showBackground = false)
@Composable
fun HeaderListPreview() {
    DetailsBook(book = BookResponseModel(
        id = 1,
        name = "test",
        author = "",
        summary = "",
        genre = "",
        coverUrl = "",
        views = "",
        likes = "",
        quotes = ""
    ),
        items = listOf(
            BookResponseModel(
                id = 1,
                name = "test",
                author = "",
                summary = "",
                genre = "",
                coverUrl = "",
                views = "",
                likes = "",
                quotes = ""
            ), BookResponseModel(
                id = 1,
                name = "test",
                author = "",
                summary = "",
                genre = "",
                coverUrl = "",
                views = "",
                likes = "",
                quotes = ""
            )
        ),
        onClickBookEventSent = {},
        onClickReadNiwEventSent = {}
    )
}


@Composable
fun Info(book: BookResponseModel?) {
    Row(modifier = Modifier.padding(top = 20.dp, start = 16.dp, end = 16.dp)) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InfoMainText(book?.views ?: stringResource(R.string.zero))
            InfoSecondText(text = stringResource(R.string.readers))
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InfoMainText(book?.likes ?: stringResource(R.string.zero))
            InfoSecondText(text = stringResource(R.string.likes))
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InfoMainText(book?.quotes ?: stringResource(R.string.zero))
            InfoSecondText(text = stringResource(R.string.quotes))
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                InfoMainText(stringResource(R.string.hot))
                Image(
                    painter = painterResource(id = R.drawable.ic_pepper),
                    contentDescription = "",
                    Modifier.padding(start = 2.dp)
                )
            }

            InfoSecondText(text = stringResource(R.string.genre))
        }
    }
}

@Composable
fun InfoMainText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            color = MainTextInfo,
            fontSize = 18.sp,
            lineHeight = 22.sp,
            letterSpacing = (-0.41).sp,
            fontFamily = FontFamily(
                Font(R.font.nunito_sans_bold)
            )
        ),
        modifier = Modifier
    )
}

@Composable
fun InfoSecondText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            color = TextInfo,
            fontSize = 12.sp,
            lineHeight = 13.2.sp,
            letterSpacing = (-0.41).sp,
            fontFamily = FontFamily(
                Font(R.font.nunito_sans_regular)
            )
        ),
        modifier = Modifier
    )
}

@Composable
fun InfoTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(top = 16.dp, start = 16.dp),
        style = TextStyle(
            color = MainTextInfo,
            fontSize = 20.sp,
            lineHeight = 22.sp,
            letterSpacing = (-0.41).sp,
            fontFamily = FontFamily(
                Font(R.font.nunito_sans_bold)
            )
        )
    )
}

@Composable
fun SummaryText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
        style = TextStyle(
            color = SummaryInfo,
            fontSize = 14.sp,
            lineHeight = 16.8.sp,
            letterSpacing = (0.15).sp,
            fontFamily = FontFamily(
                Font(R.font.nunito_sans_regular)
            )
        )
    )
}


@Composable
fun SpacerInfo() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .background(SpacerInfo)
            .height(1.dp)
    )
}

@Composable
fun ReadNowButton(
    onReadNowEvent: () -> Unit
) {
    Button(
        onClick = { onReadNowEvent.invoke() },
        colors = ButtonColors(ReadNowBtnColor, ReadNowBtnColor, ReadNowBtnColor, ReadNowBtnColor),
        modifier = Modifier
            .padding(start = 49.dp, end = 49.dp, top = 8.dp, bottom = 52.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.read_now),
            modifier = Modifier.padding(8.dp),
            style = TextStyle(
                color = White,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontFamily = FontFamily(
                    Font(R.font.nunito_sans_bold)
                )
            )
        )
    }
}

@Composable
fun HorizontalItems(
    items: List<BookResponseModel>,
    onEventSent: (bookId: Int) -> Unit
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .padding(16.dp)
            .horizontalScroll(scrollState)
    ) {
        items.forEach { item ->
            Column(
                modifier = Modifier
                    .width(120.dp)
                    .padding(end = 8.dp)
            ) {
                val painter = rememberAsyncImagePainter(item.coverUrl)
                Image(
                    painter = painter,
                    modifier = Modifier
                        .width(120.dp)
                        .height(150.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(PreviewImage)
                        .clickable {
                            onEventSent.invoke(item.id)
                        },
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = item.name,
                    style = TextStyle(
                        color = ListTextInfo,
                        fontSize = 16.sp,
                        lineHeight = 17.6.sp,
                        letterSpacing = (-0.41).sp,
                        fontFamily = FontFamily(
                            Font(R.font.nunito_sans_regular)
                        )
                    )
                )
            }
        }
    }
}
