package com.example.logicgames.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Entity class representing an attempt made in a game.
 * @param id The unique identifier for the attempt.
 * @param time The time when the attempt was made.
 * @param game The name of the game.
 * @param gameRoute The route of the game.
 * @param score The score achieved in the attempt.
 *
 * Modified from: https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#2
 */
@Entity(tableName = "attempts")
data class Attempt(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val time: Date,
    val game: String,
    val gameRoute: String,
    val score: Int
)

/**
 * Formats the date of the attempt.
 * @return A formatted date string.
 */
fun Attempt.formatedDate(): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
    return dateFormat.format(time)
}

/**
 * Formats the score of the attempt.
 * @return A formatted score string.
 */
fun Attempt.formatedScore(): String {
    return score.toString()
}