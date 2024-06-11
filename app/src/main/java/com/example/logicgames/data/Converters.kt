package com.example.logicgames.data

import androidx.room.TypeConverter
import java.util.Date

/**
 * Class containing TypeConverters for converting between Date and Long timestamps.
 */
class Converters {
    /**
     * Converts a Long timestamp to a Date object.
     * @param value The Long timestamp to convert.
     * @return The Date object.
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    /**
     * Converts a Date object to a Long timestamp.
     * @param date The Date object to convert.
     * @return The Long timestamp.
     */
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}