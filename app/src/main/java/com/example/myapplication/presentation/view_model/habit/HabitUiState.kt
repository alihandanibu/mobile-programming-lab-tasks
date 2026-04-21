package com.example.myapplication.presentation.view_model.habit

import com.example.myapplication.ui.screens.HabitModel

sealed interface HabitUiState {
    data object Init : HabitUiState
    data object Loading : HabitUiState
    data class Success(val habits: List<HabitModel>) : HabitUiState
    data class Error(val message: String) : HabitUiState
}
