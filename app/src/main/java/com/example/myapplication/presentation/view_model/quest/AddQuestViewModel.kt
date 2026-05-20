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

sealed class AddQuestUiState {
    object Idle : AddQuestUiState()
    object Loading : AddQuestUiState()
    object Success : AddQuestUiState()
    data class Error(val message: String) : AddQuestUiState()
}

@HiltViewModel
class AddQuestViewModel @Inject constructor(
    private val questRepository: QuestRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AddQuestUiState>(AddQuestUiState.Idle)
    val uiState: StateFlow<AddQuestUiState> = _uiState.asStateFlow()

    fun addQuest(
        questTitle: String,
        xpReward: String,
        category: String,
        difficulty: String,
        isDaily: Boolean
    ) {
        viewModelScope.launch {
            _uiState.value = AddQuestUiState.Loading

            try {
                if (questTitle.isBlank()) {
                    _uiState.value = AddQuestUiState.Error("Quest title cannot be empty")
                    return@launch
                }

                val xp = xpReward.toIntOrNull()

                if (xp == null || xp <= 0) {
                    _uiState.value = AddQuestUiState.Error("XP reward must be a positive number")
                    return@launch
                }

                questRepository.addQuest(
                    title = questTitle,
                    xp = xp,
                    category = category,
                    difficulty = difficulty,
                    isDaily = isDaily
                )

                _uiState.value = AddQuestUiState.Success
            } catch (e: Exception) {
                _uiState.value = AddQuestUiState.Error(
                    e.message ?: "Failed to add quest"
                )
            }
        }
    }

    fun resetUiState() {
        _uiState.value = AddQuestUiState.Idle
    }
}