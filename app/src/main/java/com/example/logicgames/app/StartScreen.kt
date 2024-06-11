package com.example.logicgames.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.logicgames.R

/**
 * Composable function for displaying the start screen.
 * @param navController The NavController used for navigation.
 */
@Composable
fun StartScreen(navController: NavController) {
    Image(painter = painterResource(id = R.drawable.background_start),
        contentDescription = stringResource(id = R.string.app_name),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_transparent),
            contentDescription = "logo",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(250.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { navController.navigate("menu") }) {
            Text(stringResource(R.string.start))
        }
    }
}