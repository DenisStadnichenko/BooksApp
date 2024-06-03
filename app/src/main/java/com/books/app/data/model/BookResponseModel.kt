package com.books.app.data.model

import com.google.gson.annotations.SerializedName

data class BookResponseModel(
    val id: Int,
    val name: String,
    val author: String,
    val summary: String,
    val genre: String,
    @SerializedName("cover_url")
    val coverUrl: String,
    val views: String,
    val likes: String,
    val quotes: String
)

data class TopBook(
    val id: Int,
    val book: String,
    val cover: String,
)

data class BooksResponseModel(
    val books: List<BookResponseModel>,
    @SerializedName("top_banner_slides")
    val top: List<TopBook>,
    @SerializedName("you_will_like_section")
    val like: List<Int>
)