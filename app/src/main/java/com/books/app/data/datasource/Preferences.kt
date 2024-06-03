package com.books.app.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

class Preferences(
    private val context: Context
) {

    suspend fun addScore(volume: String) {
        context.dataStore.edit { preferences ->
            preferences[SCORE] = volume
        }
    }

    fun scopeFlow() = context.dataStore.data.catch { exception ->
        if (exception is java.io.IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        preferences[SCORE]
    }


    companion object {
        val SCORE = stringPreferencesKey(name = "score")
    }
}