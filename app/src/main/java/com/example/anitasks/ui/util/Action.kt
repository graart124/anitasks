package com.example.anitasks.ui.util

data class Action(
    val info: String? = null,
    val success: Boolean? = null
)

data class ActionWithData<T>(
    val info: String? = null,
    val success: Boolean? = null,
    val data: T? = null
)
