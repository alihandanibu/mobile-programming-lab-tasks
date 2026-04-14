package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeShortcutScreen(
    onQuestClick: () -> Unit,
    onHabitClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home Shortcut Screen")

        Button(
            onClick = onQuestClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Go to Quest")
        }

        Button(
            onClick = onHabitClick,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Go to Habit")
        }

        Button(
            onClick = onProfileClick,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Go to Profile")
        }
    }
}