package com.example.anitasks.core.data.local.storage

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
private const val SECRET_PREFS = "secret_shared_prefs"

class EncryptedStorageDataSource @Inject constructor(
    @ApplicationContext val context: Context
) {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    init {
        setEncryptedPref()
    }

    @Synchronized
    private fun setEncryptedPref() {
        sharedPreferences = context.getSharedPreferences(
            SECRET_PREFS,
            Context.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()
    }

    fun save(key: String, value: String) = editor.run {
        putString(key, value)
        apply()
    }

    fun get(key: String): String? {
        val value = sharedPreferences.getString(key, "")
        if (value.isNullOrBlank()) return null
        return value
    }

    fun clear(key: String) =
        editor.run { remove(key).apply() }
}