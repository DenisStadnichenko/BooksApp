import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.books.app.R
import com.books.app.data.model.BookResponseModel
import com.books.app.ui.theme.MainBackground
import com.books.app.ui.theme.PreviewImage
import com.books.app.ui.theme.WhiteAlpha70

data class Group(val title: String, val books: List<BookResponseModel>)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickyHeaderList(
    groups: List<Group>,
    modifier: Modifier = Modifier,
    onEventSent: (bookId: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .background(MainBackground)
            .padding(start = 16.dp, top = 20.dp, end = 16.dp)
    ) {
        groups.forEach { group ->
            stickyHeader {
                GroupHeader(title = group.title)
            }
            item {
                HorizontalItemList(items = group.books, onEventSent)
            }
        }
    }
}

@Composable
fun GroupHeader(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MainBackground)
            .padding(top = 24.dp, bottom = 14.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 22.sp,
            fontFamily = FontFamily(
                Font(R.font.nunito_sans_bold)
            )
        )
    }
}

@Composable
fun HorizontalItemList(
    items: List<BookResponseModel>,
    onEventSent: (bookId: Int) -> Unit
) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
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
                        .clickable { onEventSent.invoke(item.id) },
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = item.name,
                    color = WhiteAlpha70,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(R.font.nunito_sans_regular)
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    StickyHeaderList(
        groups = listOf(
            Group(
                title = "Test",
                books = listOf(
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
                )
            ),
            Group(
                title = "Test",
                books = listOf(
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
                )
            ),
            Group(
                title = "Test",
                books = listOf(
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
                )
            )
        ),
        onEventSent = {}
    )
}

