package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.view_model.quest.AddQuestUiState
import com.example.myapplication.presentation.view_model.quest.AddQuestViewModel

@Composable
fun AddQuestScreen(
    modifier: Modifier = Modifier,
    onQuestAdded: () -> Unit = {},
    viewModel: AddQuestViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var xp by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var difficulty by remember { mutableStateOf("") }
    var isDaily by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is AddQuestUiState.Success) {
            viewModel.resetUiState()
            onQuestAdded()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Add Quest",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Quest title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = xp,
            onValueChange = { xp = it },
            label = { Text("XP reward") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = difficulty,
            onValueChange = { difficulty = it },
            label = { Text("Difficulty") },
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            Checkbox(
                checked = isDaily,
                onCheckedChange = { isDaily = it }
            )

            Text(
                text = "Daily quest",
                modifier = Modifier.padding(top = 12.dp)
            )
        }

        when (val state = uiState) {
            is AddQuestUiState.Loading -> {
                CircularProgressIndicator()
            }

            is AddQuestUiState.Error -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> Unit
        }

        Button(
            onClick = {
                viewModel.addQuest(
                    questTitle = title,
                    xpReward = xp,
                    category = category,
                    difficulty = difficulty,
                    isDaily = isDaily
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Quest")
        }
    }
}