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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.DeleteQuestDialog
import com.example.myapplication.ui.components.QuestItem
import com.example.myapplication.ui.components.Title
import com.example.myapplication.util.QuestData

@Composable
fun QuestScreen(
    modifier: Modifier = Modifier
) {
    var quests by remember {
        mutableStateOf(
            listOf(
                QuestData(1, "Study Kotlin", 20, false),
                QuestData(2, "Workout", 15, true),
                QuestData(3, "Drink Water", 10, false),
                QuestData(4, "Read 10 Pages", 25, false)
            )
        )
    }

    var questToDelete by remember {
        mutableStateOf<QuestData?>(null)
    }

    QuestScreen(
        quests = quests,
        onCheckedChange = { id, isChecked ->
            quests = quests.map { quest ->
                if (quest.id == id) {
                    quest.copy(isCompleted = isChecked)
                } else {
                    quest
                }
            }
        },
        onDeleteClick = { id ->
            questToDelete = quests.find { it.id == id }
        },
        onAddQuestClick = {
            val nextId = (quests.maxOfOrNull { it.id } ?: 0) + 1
            quests = quests + QuestData(
                id = nextId,
                title = "New Quest $nextId",
                xp = 10,
                isCompleted = false
            )
        },
        modifier = modifier
    )

    questToDelete?.let { selectedQuest ->
        DeleteQuestDialog(
            questTitle = selectedQuest.title,
            onConfirm = {
                quests = quests.filter { it.id != selectedQuest.id }
                questToDelete = null
            },
            onDismiss = {
                questToDelete = null
            }
        )
    }
}

@Composable
private fun QuestScreen(
    quests: List<QuestData>,
    onCheckedChange: (Int, Boolean) -> Unit,
    onDeleteClick: (Int) -> Unit,
    onAddQuestClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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

            quests.forEach { quest ->
                QuestItem(
                    quest = quest,
                    onCheckedChange = { isChecked ->
                        onCheckedChange(quest.id, isChecked)
                    },
                    onDeleteClick = {
                        onDeleteClick(quest.id)
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun QuestScreenPreview() {
    QuestScreen(
        quests = listOf(
            QuestData(1, "Study Kotlin", 20, false),
            QuestData(2, "Workout", 15, true),
            QuestData(3, "Drink Water", 10, false)
        ),
        onCheckedChange = { _, _ -> },
        onDeleteClick = {},
        onAddQuestClick = {}
    )
}