package com.example.myapplication.presentation.view_model.quest

import com.example.myapplication.model.data.remote.QuestData

sealed class QuestUiState {
    object Idle : QuestUiState()
    object Loading : QuestUiState()
    data class Success(val quests: List<QuestData>) : QuestUiState()
    data class Error(val message: String) : QuestUiState()
}