package com.books.app.presentations.screens.details.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.rememberAsyncImagePainter
import com.books.app.R
import com.books.app.data.model.BookResponseModel
import com.books.app.ui.theme.White
import com.books.app.ui.theme.WhiteAlpha80
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@SuppressLint("UnusedBoxWithConstraintsScope", "UnrememberedMutableInteractionSource")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPager(
    currentBook: BookResponseModel,
    books: List<BookResponseModel>,
    onBookChanged: (BookResponseModel) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {

        val itemSpacing = 0.dp
        val pagerState = rememberPagerState(pageCount = {
            books.size
        })

        val scope = rememberCoroutineScope()


        LaunchedEffect(key1 = currentBook) {
            val selectedIndex = books.indexOf(currentBook)
            if (selectedIndex != -1) {
                scope.launch {
                    pagerState.animateScrollToPage(selectedIndex)
                }
            }
        }

        LaunchedEffect(pagerState.currentPage) {
            if (books.isNotEmpty()) {
                val current = books[pagerState.currentPage]
                //  onEventSent(DetailsContract.Event.DetailsBook(currentBook))
                onBookChanged.invoke(current)
            }
        }

        HorizontalPager(
            modifier = modifier,
            state = pagerState,
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState,
                pagerSnapDistance = PagerSnapDistance.atMost(0)
            ),
            pageSize = PageSize.Fixed(200.dp),
            contentPadding = PaddingValues(horizontal = 88.dp),
            pageSpacing = itemSpacing
        ) { page ->
            val book = books[page]
            val painter = rememberAsyncImagePainter(book.coverUrl)

            Column(
                modifier = Modifier
                    .width(200.dp)
                    .padding(horizontal = itemSpacing / 2)
            ) {
                Image(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .height(250.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            enabled = true,
                        ) {
                            scope.launch {
                                pagerState.animateScrollToPage(page)
                            }
                        }
                        .graphicsLayer {
                            val pageOffSet = (
                                    (pagerState.currentPage - page) + pagerState
                                        .currentPageOffsetFraction
                                    ).absoluteValue
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffSet.coerceIn(0f, 1f)
                            )
                            scaleY = lerp(
                                start = 0.75f,
                                stop = 1f,
                                fraction = 1f - pageOffSet.coerceIn(0f, 1f)
                            )
                        },
                    contentScale = ContentScale.Crop
                )
                Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                    Text(
                        text = book.name,
                        modifier = Modifier
                            .padding(
                                top = 14.dp,
                                start = 16.dp,
                                end = 16.dp
                            )
                            .align(Alignment.Center),
                        style = TextStyle(
                            color = White,
                            fontSize = 20.sp,
                            lineHeight = 22.sp,
                            letterSpacing = (-0.41).sp,
                            fontFamily = FontFamily(
                                Font(R.font.nunito_sans_regular)
                            )
                        )
                    )
                }


                Text(
                    text = book.author,
                    modifier = Modifier
                        .padding(
                            top = 4.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                    style = TextStyle(
                        color = WhiteAlpha80,
                        fontSize = 14.sp,
                        lineHeight = 15.4.sp,
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

@Preview(showBackground = false)
@Composable
fun HorizontalScalingListPreview() {
    HorizontalPager(
        currentBook = BookResponseModel(
            id = 1,
            name = "test",
            author = "tert",
            summary = "",
            genre = "",
            coverUrl = "",
            views = "",
            likes = "",
            quotes = ""
        ),
        books = listOf(
            BookResponseModel(
                id = 1,
                name = "test",
                author = "tert",
                summary = "",
                genre = "",
                coverUrl = "",
                views = "",
                likes = "",
                quotes = ""
            ), BookResponseModel(
                id = 1,
                name = "test",
                author = "tert",
                summary = "",
                genre = "",
                coverUrl = "",
                views = "",
                likes = "",
                quotes = ""
            ), BookResponseModel(
                id = 1,
                name = "test",
                author = "test",
                summary = "",
                genre = "",
                coverUrl = "",
                views = "",
                likes = "",
                quotes = ""
            )
        ),
        onBookChanged = {}

    )
}