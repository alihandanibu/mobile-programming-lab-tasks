package com.example.myapplication.presentation.view_model.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = LoginUiState.Error("Please fill all fields")
            return
        }
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            try {
                authRepository.login(email, password)
                _uiState.value = LoginUiState.Success
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error(e.message ?: "Login failed")
            }
        }
    }

    fun register(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = LoginUiState.Error("Please fill all fields")
            return
        }
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            try {
                authRepository.register(email, password)
                _uiState.value = LoginUiState.Success
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error(e.message ?: "Registration failed")
            }
        }
    }

    fun onGoogleSignInResult(idToken: String?) {
        if (idToken == null) {
            _uiState.value = LoginUiState.Error("Google Sign-In failed")
            return
        }
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            try {
                authRepository.signInWithGoogle(idToken)
                _uiState.value = LoginUiState.Success
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error(e.message ?: "Google Auth failed")
            }
        }
    }

    fun resetUiState() {
        _uiState.value = LoginUiState.Idle
    }
}
