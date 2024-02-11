package com.example.anitasks.core.data.local.room.convertors

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter


object LocalDateConvertor {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    @JvmStatic
    fun fromLocalDateTime(value: LocalDate?): String? {
        return value?.format(formatter)
    }

    @TypeConverter
    @JvmStatic
    fun toLocalDateTime(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it, formatter) }
    }
}