package com.example.myapplication.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DeleteQuestDialog(
    questTitle: String
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = "Delete Quest")
        },
        text = {
            Text(text = "Are you sure you want to delete \"$questTitle\"?")
        },
        confirmButton = {
            TextButton(onClick = {}) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = {}) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DeleteQuestDialogPreview() {
    DeleteQuestDialog(questTitle = "Study Kotlin")
}