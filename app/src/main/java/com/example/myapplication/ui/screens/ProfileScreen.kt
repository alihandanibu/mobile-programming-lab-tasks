package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.presentation.view_model.profile.ProfileUiState
import com.example.myapplication.presentation.view_model.profile.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        ProfileUiState.Loading -> {
            LoadingScreen()
        }

        is ProfileUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetry = { viewModel.resetUiState() }
            )
        }

        is ProfileUiState.Success -> {
            ProfileScreenContent(profileData = state.profileData)
        }

        ProfileUiState.Init -> { }
    }
}

@Composable
fun ProfileScreenContent(
    profileData: ProfileData
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Screen")
        Text(text = "Full Name: ${profileData.fullName}", modifier = Modifier.padding(top = 12.dp))
        Text(text = "Email: ${profileData.email}", modifier = Modifier.padding(top = 8.dp))
        Text(text = "Level: ${profileData.level}", modifier = Modifier.padding(top = 8.dp))
    }
}
