package com.example.myapplication.presentation.view_model.habit

import com.example.myapplication.ui.screens.HabitModel

data class HabitUiState(
    val isLoading: Boolean = false,
    val habits: List<HabitModel> = emptyList(),
    val errorMessage: String? = null
)
