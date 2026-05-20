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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    LaunchedEffect(Unit) {
        viewModel.loadQuests()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
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

        FloatingActionButton(
            onClick = onAddQuestClick,
            modifier = Modifier.align(Alignment.BottomEnd),
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