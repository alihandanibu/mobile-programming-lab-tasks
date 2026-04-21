package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.presentation.view_model.habit.HabitNavigationEvent
import com.example.myapplication.presentation.view_model.habit.HabitUiState
import com.example.myapplication.presentation.view_model.habit.HabitViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HabitsScreen(
    viewModel: HabitViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collectLatest { event ->
            when (event) {
                HabitNavigationEvent.Navigate -> { }
                HabitNavigationEvent.NavigateBack -> { }
            }
        }
    }

    when (val state = uiState) {
        HabitUiState.Loading -> {
            LoadingScreen()
        }

        is HabitUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetry = { viewModel.resetUiState() }
            )
        }

        is HabitUiState.Success -> {
            HabitsScreenContent(habits = state.habits)
        }

        HabitUiState.Init -> { }
    }
}

@Composable
fun HabitsScreenContent(
    habits: List<HabitModel>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Habits Screen")

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            items(habits) { habit ->
                Column(modifier = Modifier.padding(vertical = 12.dp)) {
                    Text(text = habit.title)
                    Text(text = habit.description)
                }
            }
        }
    }
}
