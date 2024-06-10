package com.example.logicgames.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Level(
    val number: Int = 1,
    val description: String = ""
)

@Composable
fun LevelSelection(levels:List<Level>, selectedLevel: Int, onLevelSelected: (Int) -> Unit) {
    Column (
        horizontalAlignment = Alignment.Start
    ) {
        levels.forEach { level ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                RadioButton(
                    selected = (level.number == selectedLevel),
                    onClick = { onLevelSelected(level.number) }
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Level ${level.number} (${level.description})")
            }
        }
    }
}