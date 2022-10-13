package com.andriawan.askme.data.local.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.andriawan.askme.utils.orFalse
import com.andriawan.askme.utils.orTrue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = AskMeDataStore.PREFERENCE_NAME)

class AskMeDataStore(private val context: Context) {

    suspend fun getFirstTime(): Boolean = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                Log.e(
                    AskMeDataStore::class.simpleName,
                    "getFirstTime: ${exception.localizedMessage}"
                )
            }
        }
        .map { preferences ->
            preferences[firstTimeKey].orTrue()
        }.first()

    suspend fun setFirstTime(isFirstTime: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[firstTimeKey] = isFirstTime
        }
    }

    companion object {
        const val PREFERENCE_NAME = "ask_me_preferences"
        val firstTimeKey = booleanPreferencesKey("first_time_key")
    }
}