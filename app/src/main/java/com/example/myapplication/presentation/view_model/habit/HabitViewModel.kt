package com.example.myapplication.presentation.view_model.habit

import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.screens.HabitModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel
class HabitViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<HabitUiState>(HabitUiState.Init)
    val uiState: StateFlow<HabitUiState> = _uiState.asStateFlow()

    private val _navigationEvent = Channel<HabitNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    init {
        loadHabits()
    }

    fun loadHabits() {
        _uiState.value = HabitUiState.Loading

        val habits = listOf(
            HabitModel(1, "Morning Training", "Start the day with movement"),
            HabitModel(2, "Read 10 Pages", "Build daily reading habit"),
            HabitModel(3, "Drink Water", "Stay hydrated during the day")
        )

        _uiState.value = HabitUiState.Success(habits)
    }

    fun resetUiState() {
        loadHabits()
    }
}
