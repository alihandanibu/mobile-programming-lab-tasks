package com.example.myapplication.presentation.view_model.habit

import com.example.myapplication.model.data.remote.dto.HabitDto

data class HabitUiState(
    val isLoading: Boolean = false,
    val habits: List<HabitDto> = emptyList(),
    val errorMessage: String? = null
)