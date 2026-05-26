package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.view_model.quest.QuestUiState
import com.example.myapplication.presentation.view_model.quest.QuestViewModel
import com.example.myapplication.ui.components.QuestItem
import com.example.myapplication.ui.components.Title

@Composable
fun QuestsScreen(
    modifier: Modifier = Modifier,
    onAddQuestClick: () -> Unit = {},
    viewModel: QuestViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val exportMessage by viewModel.exportMessage.collectAsState()
    val exportError by viewModel.exportError.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.loadQuests()
    }

    LaunchedEffect(exportMessage, exportError) {
        exportMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.clearExportState()
        }

        exportError?.let { error ->
            snackbarHostState.showSnackbar(error)
            viewModel.clearExportState()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Top
            ) {
                Title(
                    title = "Quests",
                    color = Color(0xFF0F4C5C)
                )

                Spacer(modifier = Modifier.height(16.dp))

                when (val state = uiState) {
                    is QuestUiState.Idle -> {
                        Text(text = "No quests loaded yet")
                    }

                    is QuestUiState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is QuestUiState.Success -> {
                        state.quests.forEach { quest ->
                            QuestItem(
                                quest = quest,
                                onCheckedChange = { checked ->
                                    viewModel.toggleQuest(
                                        questId = quest.id,
                                        isCompleted = checked
                                    )
                                },
                                onDeleteClick = {
                                    viewModel.deleteQuest(quest.id)
                                }
                            )

                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }

                    is QuestUiState.Error -> {
                        Text(
                            text = state.message,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.align(Alignment.BottomEnd),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.End
            ) {
                FloatingActionButton(
                    onClick = {
                        val currentState = uiState

                        if (currentState is QuestUiState.Success) {
                            viewModel.exportQuests(currentState.quests)
                        }
                    },
                    containerColor = Color(0xFF0F4C5C)
                )  {
                    Text(
                        text = "CSV",
                        color = Color(0xFFF0F8FF)
                    )
                }

                FloatingActionButton(
                    onClick = onAddQuestClick,
                    containerColor = Color(0xFF0F4C5C)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add quest",
                        tint = Color(0xFFF0F8FF)
                    )
                }
            }
        }
    }
}