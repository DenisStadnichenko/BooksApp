package com.books.app.data.api

import android.util.Log
import com.books.app.data.model.BooksResponseModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

interface BooksService {
    fun getBooks(): Flow<BooksResponseModel>
}

class BookServiceImp : BooksService {

    override fun getBooks() = flow {
        val rc = FirebaseRemoteConfig.getInstance()
        rc.fetchAndActivate().await()

        val run = runCatching {
            val jsonData = rc.getString("json_data")
            Log.i("TAgfff", "jsonData:$jsonData")

            val gson = Gson()
            val booksResponse = gson.fromJson(jsonData, BooksResponseModel::class.java)
            Log.i("TAgfff", "booksResponse:$booksResponse")
            booksResponse
        }

        delay(2_000)
        when {
            run.isSuccess -> {
                val response = run.getOrNull()
                if (response == null) {
                    error("Books response is null")
                } else {
                    emit(response)
                }
            }

            run.isFailure -> error("Get books failed")
        }
    }
}