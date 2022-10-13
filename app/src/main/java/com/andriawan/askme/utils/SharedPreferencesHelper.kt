package com.andriawan.askme.utils

import android.content.Context
import android.content.SharedPreferences
import com.andriawan.askme.BuildConfig
import com.andriawan.askme.utils.Constants.EMPTY

object SharedPreferencesHelper {

    private fun getSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(
            "${BuildConfig.APPLICATION_ID}_preferences",
            Context.MODE_PRIVATE
        )

    fun saveString(context: Context, key: String, value: String) {
        val prefs = getSharedPreferences(context)
        prefs.edit().putString(key, value).apply()
    }

    fun getString(context: Context, key: String, defaultValue: String = EMPTY): String {
        val prefs = getSharedPreferences(context)
        return prefs.getString(key, defaultValue).orEmpty()
    }
}