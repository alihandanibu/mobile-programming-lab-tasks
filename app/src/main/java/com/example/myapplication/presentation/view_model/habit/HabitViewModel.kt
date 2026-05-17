package com.example.myapplication.presentation.view_model.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.datasource.network.dto.CreateHabitDto
import com.example.myapplication.model.datasource.network.dto.UpdateHabitDto
import com.example.myapplication.model.datasource.network.mapper.toDomain
import com.example.myapplication.model.repository.habit.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
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

    init {
        loadHabits()
    }

    fun loadHabits() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                val habitsDto = habitRepository.getHabits()
                val habits = habitsDto.map { it.toDomain() }
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    habits = habits
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Failed to load habits"
                )
            }
        }
    }

    fun addHabit(title: String, frequency: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                habitRepository.createHabit(CreateHabitDto(title, frequency))
                loadHabits() // Refresh list
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Failed to create habit"
                )
            }
        }
    }

    fun deleteHabit(id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                habitRepository.deleteHabit(id)
                loadHabits() // Refresh list
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Failed to delete habit"
                )
            }
        }
    }

    fun updateHabitStreak(id: Int, newStreak: Int) {
        viewModelScope.launch {
            try {
                habitRepository.updateHabit(id, UpdateHabitDto(frequency = newStreak))
                loadHabits()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = e.message ?: "Failed to update habit"
                )
            }
        }
    }
}
