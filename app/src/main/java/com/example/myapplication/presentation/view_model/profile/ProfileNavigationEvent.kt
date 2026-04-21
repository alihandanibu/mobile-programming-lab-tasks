package com.example.myapplication.presentation.view_model.profile

sealed interface ProfileNavigationEvent {
    data object Navigate : ProfileNavigationEvent
    data object NavigateBack : ProfileNavigationEvent
}
