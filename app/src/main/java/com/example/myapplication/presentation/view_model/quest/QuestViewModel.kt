package com.example.myapplication.presentation.view_model.quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.repository.quest.QuestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestViewModel @Inject constructor(
    private val questRepository: QuestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<QuestUiState>(QuestUiState.Idle)
    val uiState: StateFlow<QuestUiState> = _uiState.asStateFlow()

    fun loadQuests() {
        viewModelScope.launch {
            _uiState.value = QuestUiState.Loading

            try {
                val quests = questRepository.getQuests()
                _uiState.value = QuestUiState.Success(quests)
            } catch (e: Exception) {
                _uiState.value = QuestUiState.Error(
                    e.message ?: "Failed to load quests"
                )
            }
        }
    }

    fun toggleQuest(questId: String, isCompleted: Boolean) {
        viewModelScope.launch {
            try {
                questRepository.toggleQuest(
                    questId = questId,
                    isCompleted = isCompleted
                )

                loadQuests()
            } catch (e: Exception) {
                _uiState.value = QuestUiState.Error(
                    e.message ?: "Failed to update quest"
                )
            }
        }
    }

    fun deleteQuest(questId: String) {
        viewModelScope.launch {
            try {
                questRepository.deleteQuest(questId)
                loadQuests()
            } catch (e: Exception) {
                _uiState.value = QuestUiState.Error(
                    e.message ?: "Failed to delete quest"
                )
            }
        }
    }

    fun resetUiState() {
        _uiState.value = QuestUiState.Idle
    }
}