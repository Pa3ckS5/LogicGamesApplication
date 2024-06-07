package com.example.logicgames.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "attempts")
data class Attempt(
    @PrimaryKey(autoGenerate = true) val attempt_id: Int = 0,
    val time: Date,
    val game: String,
    val score: Int
)
