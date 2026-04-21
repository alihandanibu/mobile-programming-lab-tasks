package com.example.myapplication.presentation.view_model.home

sealed interface HomeShortcutNavigationEvent {
    data object Navigate : HomeShortcutNavigationEvent
    data object NavigateBack : HomeShortcutNavigationEvent
}
