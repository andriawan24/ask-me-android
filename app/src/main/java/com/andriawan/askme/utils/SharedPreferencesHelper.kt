package com.andriawan.askme.utils

import android.content.Context
import android.content.SharedPreferences
import com.andriawan.askme.BuildConfig
import com.andriawan.askme.utils.Constants.EMPTY

class SharedPreferencesHelper(private val context: Context) {

    private fun getSharedPreferences(): SharedPreferences =
        context.getSharedPreferences(
            "${BuildConfig.APPLICATION_ID}_preferences",
            Context.MODE_PRIVATE
        )

    fun saveString(key: String, value: String) {
        val prefs = getSharedPreferences()
        prefs.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String = EMPTY): String {
        val prefs = getSharedPreferences()
        return prefs.getString(key, defaultValue).orEmpty()
    }

    fun clearPreference(key: String) {
        val prefs = getSharedPreferences()
        prefs.edit().remove(key).apply()
    }
}