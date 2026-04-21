package com.example.myapplication.presentation.view_model.profile

import com.example.myapplication.ui.screens.ProfileData

sealed interface ProfileUiState {
    data object Init : ProfileUiState
    data object Loading : ProfileUiState
    data class Success(val profileData: ProfileData) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}
