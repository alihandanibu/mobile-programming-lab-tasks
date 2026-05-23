package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.view_model.quest.AddQuestUiState
import com.example.myapplication.presentation.view_model.quest.AddQuestViewModel

@OptIn(ExperimentalMaterial3Api::class)
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

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Add New Quest") },
                    navigationIcon = {
                        IconButton(onClick = onQuestAdded) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isDaily,
                        onCheckedChange = { isDaily = it }
                    )
                    Text(text = "Daily quest")
                }

                Spacer(modifier = Modifier.weight(1f))

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
                    modifier = Modifier.fillMaxWidth(),
                    enabled = uiState !is AddQuestUiState.Loading
                ) {
                    Text("Save Quest")
                }

                if (uiState is AddQuestUiState.Error) {
                    Text(
                        text = (uiState as AddQuestUiState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        // FULL SCREEN LOADING OVERLAY
        if (uiState is AddQuestUiState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
