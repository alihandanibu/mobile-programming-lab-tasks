package com.example.myapplication.presentation.view_model.quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.data.remote.QuestData
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

    private val _exportMessage = MutableStateFlow<String?>(null)
    val exportMessage: StateFlow<String?> = _exportMessage.asStateFlow()

    private val _exportError = MutableStateFlow<String?>(null)
    val exportError: StateFlow<String?> = _exportError.asStateFlow()

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

    fun exportQuests(quests: List<QuestData>) {
        viewModelScope.launch {
            val result = questRepository.exportQuests(quests)

            if (result.isSuccess) {
                _exportMessage.value = "Quests exported to Downloads folder"
            } else {
                _exportError.value = result.exceptionOrNull()?.message ?: "Failed to export quests"
            }
        }
    }

    fun clearExportState() {
        _exportMessage.value = null
        _exportError.value = null
    }

    fun resetUiState() {
        _uiState.value = QuestUiState.Idle
    }
}