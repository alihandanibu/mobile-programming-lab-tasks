package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StateBasicsScreen() {
    var counter by remember { mutableIntStateOf(0) }
    var text by rememberSaveable { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    val doubleCounter by remember {
        derivedStateOf { counter * 2 }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "State Basics Practice",
            style = MaterialTheme.typography.titleLarge
        )

        Text(text = "Counter: $counter")

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = { counter-- }) {
                Text("-")
            }

            Button(onClick = { counter++ }) {
                Text("+")
            }
        }

        Text(text = "Double Counter: $doubleCounter")

        HorizontalDivider()

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter something") }
        )

        Text(text = "You typed: $text")

        HorizontalDivider()

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Text(text = "Check me")
        }

        Text(
            text = if (isChecked) "Checked" else "Not checked"
        )
    }
}