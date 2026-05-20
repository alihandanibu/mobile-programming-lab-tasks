package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.data.remote.QuestData

@Composable
fun QuestItem(
    quest: QuestData,
    onCheckedChange: (Boolean) -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(quest.title, style = MaterialTheme.typography.titleMedium)
                Text("XP: ${quest.xp}", style = MaterialTheme.typography.bodyMedium)
                Text("${quest.category} • ${quest.difficulty}", style = MaterialTheme.typography.bodySmall)

                if (quest.isDaily) {
                    Text("Daily quest", style = MaterialTheme.typography.bodySmall)
                }
            }

            Checkbox(
                checked = quest.isCompleted,
                onCheckedChange = onCheckedChange
            )

            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete quest"
                )
            }
        }
    }
}