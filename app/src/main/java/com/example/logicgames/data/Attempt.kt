package com.example.logicgames.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date

@Entity(tableName = "attempts")
data class Attempt(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val time: Date,
    val game: String,
    val score: Int
)

fun Attempt.formatedDate(): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
    return dateFormat.format(time)
}

fun Attempt.formatedScore(): String {
    return score.toString()
}
