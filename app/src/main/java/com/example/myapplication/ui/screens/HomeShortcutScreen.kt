package com.example.myapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.presentation.view_model.home.HomeShortcutNavigationEvent
import com.example.myapplication.presentation.view_model.home.HomeShortcutUiState
import com.example.myapplication.presentation.view_model.home.HomeShortcutViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeShortcutScreen(
    viewModel: HomeShortcutViewModel,
    onScreenClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collectLatest { event ->
            when (event) {
                HomeShortcutNavigationEvent.Navigate -> { }
                HomeShortcutNavigationEvent.NavigateBack -> { }
            }
        }
    }

    when (val state = uiState) {
        HomeShortcutUiState.Loading -> {
            LoadingScreen()
        }

        is HomeShortcutUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetry = { viewModel.resetUiState() }
            )
        }

        is HomeShortcutUiState.Success -> {
            HomeShortcutScreenContent(
                searchQuery = state.searchQuery,
                shortcuts = state.shortcuts,
                onSearchQueryChange = viewModel::onSearchQueryChange,
                onScreenClick = onScreenClick
            )
        }

        HomeShortcutUiState.Init -> { }
    }
}

@Composable
fun HomeShortcutScreenContent(
    searchQuery: String,
    shortcuts: List<ScreenShortcutData>,
    onSearchQueryChange: (String) -> Unit,
    onScreenClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Shortcut Screen")

        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            label = { Text("Search") },
            modifier = Modifier.padding(top = 16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            items(shortcuts) { shortcut ->
                Text(
                    text = shortcut.title,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .clickable { onScreenClick(shortcut.route) }
                )
            }
        }
    }
}
