package com.example.anitasks.core.data.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val userId: String?,
    val email: String?,
    val displayName: String?,
    val photoUrl: String?
)

fun UserData.toJsonString(): String {
    return Json.encodeToString(this)
}

fun String.toUserData(): UserData? {
    return try {
        Json.decodeFromString<UserData>(this)
    } catch (e: Exception) {
        null
    }
}
