package com.example.myapplication.presentation.view_model.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.repository.habit.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val habitRepository: HabitRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HabitUiState())
    val uiState: StateFlow<HabitUiState> = _uiState.asStateFlow()

    fun loadHabits() {
        viewModelScope.launch {
            _uiState.value = HabitUiState(isLoading = true)

            try {
                val habits = habitRepository.getHabits()

                _uiState.value = HabitUiState(
                    isLoading = false,
                    habits = habits
                )
            } catch (e: Exception) {
                _uiState.value = HabitUiState(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error"
                )
            }
        }
    }
}