package com.example.myapplication.presentation.view_model.home

import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.ui.screens.ScreenShortcutData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel
class HomeShortcutViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<HomeShortcutUiState>(HomeShortcutUiState.Init)
    val uiState: StateFlow<HomeShortcutUiState> = _uiState.asStateFlow()

    private val _navigationEvent = Channel<HomeShortcutNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    private val allShortcuts = listOf(
        ScreenShortcutData("Achievements", Screen.Profile.route),
        ScreenShortcutData("Add Quest", Screen.AddQuest.createRoute("home_screen")),
        ScreenShortcutData("Habits", Screen.Habit.route),
        ScreenShortcutData("Quests", Screen.Quest.route),
        ScreenShortcutData("Profile", Screen.Profile.route),
        ScreenShortcutData("Dashboard", Screen.HomeShortcut.route)
    )

    init {
        loadShortcuts()
    }

    fun loadShortcuts() {
        _uiState.value = HomeShortcutUiState.Loading
        _uiState.value = HomeShortcutUiState.Success(
            searchQuery = "",
            shortcuts = allShortcuts
        )
    }

    fun onSearchQueryChange(query: String) {
        val filteredShortcuts = allShortcuts.filter {
            it.title.contains(query, ignoreCase = true)
        }

        _uiState.value = HomeShortcutUiState.Success(
            searchQuery = query,
            shortcuts = filteredShortcuts
        )
    }

    fun resetUiState() {
        loadShortcuts()
    }
}
