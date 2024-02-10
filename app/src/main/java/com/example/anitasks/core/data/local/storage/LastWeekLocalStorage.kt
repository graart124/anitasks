package com.example.anitasks.core.data.local.storage

import javax.inject.Inject

private const val LAST_WEEK_PREFS = "last_week_prefs"

class LastWeekLocalStorage @Inject constructor(
    private val encryptedStorageDataSource: EncryptedStorageDataSource
) {
    var lastWeek: Int
        get() = encryptedStorageDataSource.get(LAST_WEEK_PREFS)?.toInt() ?: 1
        set(value) = encryptedStorageDataSource.save(LAST_WEEK_PREFS, value.toString())
}