package com.books.app.presentations.screens.main.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.books.app.data.model.TopBook
import com.books.app.presentations.screens.main.MainContract
import com.books.app.ui.theme.IndicatorColor
import com.books.app.ui.theme.SelectedIndicatorColor
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopSlider(items: List<TopBook>, onEventSent: (event: MainContract.Event) -> Unit) {
    val pagerState = rememberPagerState(pageCount = {
        items.size
    })

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextIndex = (pagerState.currentPage + 1) % items.size
            pagerState.animateScrollToPage(nextIndex)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .height(160.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val item = items[page]
            val painter = rememberAsyncImagePainter(item.cover)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable {
                            onEventSent(MainContract.Event.ClickBook(item.id))
                        },
                    contentScale = ContentScale.Crop
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            items.forEachIndexed { index, _ ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(7.dp)
                        .background(
                            if (isSelected) SelectedIndicatorColor else IndicatorColor,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun TopPreview() {
    TopSlider(
        items = listOf(TopBook(id = 1, book = "2", cover = ""), TopBook(1, "2", "")),
        onEventSent = {}
    )
}