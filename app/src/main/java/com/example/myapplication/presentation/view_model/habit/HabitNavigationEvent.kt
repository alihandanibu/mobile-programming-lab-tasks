package com.example.myapplication.presentation.view_model.habit

sealed interface HabitNavigationEvent {
    data object Navigate : HabitNavigationEvent
    data object NavigateBack : HabitNavigationEvent
}
