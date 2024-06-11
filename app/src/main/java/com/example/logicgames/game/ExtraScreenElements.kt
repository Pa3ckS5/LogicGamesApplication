package com.example.logicgames.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.logicgames.R

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
                modifier = Modifier.padding(2.dp)
            ) {
                RadioButton(
                    selected = (level.number == selectedLevel),
                    onClick = { onLevelSelected(level.number) }
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(text = stringResource(R.string.level_display,level.number, level.description) )
            }
        }
    }
}

@Composable
fun SwitchSelection(text:String, selectedOption: Boolean, onSwitch: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Switch(
            checked = selectedOption,
            onCheckedChange = { onSwitch }
        )
        Text(
            text = text,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}