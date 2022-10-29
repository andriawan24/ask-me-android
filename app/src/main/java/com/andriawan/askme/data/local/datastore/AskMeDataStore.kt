package com.andriawan.askme.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.andriawan.askme.domain.models.UserModel
import com.andriawan.askme.utils.extensions.orTrue
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = AskMeDataStore.PREFERENCE_NAME)

class AskMeDataStore(private val context: Context) {

    suspend fun getFirstTime(): Boolean = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                Timber.e("getFirstTime: " + exception.localizedMessage)
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

    suspend fun saveUserModel(userModel: UserModel?) {
        context.dataStore.edit { preferences ->
            preferences[userModelKey] = Gson().toJson(userModel)
        }
    }

    suspend fun getUserModel(): UserModel? {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    Timber.e("getFirstTime: " + exception.localizedMessage)
                }
            }.map { preferences ->
                val userString = preferences[userModelKey]
                Gson().fromJson(userString, UserModel::class.java)
            }.firstOrNull()
    }

    companion object {
        const val PREFERENCE_NAME = "ask_me_preferences"
        val firstTimeKey = booleanPreferencesKey("first_time_key")
        val userModelKey = stringPreferencesKey("user_model_key")
    }
}