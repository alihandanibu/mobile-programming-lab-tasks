package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.presentation.view_model.auth.registration.RegistrationNavigationEvent
import com.example.myapplication.presentation.view_model.auth.registration.RegistrationUiState
import com.example.myapplication.presentation.view_model.auth.registration.RegistrationViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel,
    onNavigate: () -> Unit,
    onLoginClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val isFullNameValid = fullName.isNotBlank()
    val isEmailValid = email.isNotBlank()
    val isPasswordValid = password.isNotBlank()
    val isConfirmPasswordValid = confirmPassword.isNotBlank() && confirmPassword == password
    val isRegisterEnabled =
        isFullNameValid && isEmailValid && isPasswordValid && isConfirmPasswordValid

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collectLatest { event ->
            when (event) {
                RegistrationNavigationEvent.Navigate -> onNavigate()
                RegistrationNavigationEvent.NavigateBack -> { }
            }
        }
    }

    when (val state = uiState) {
        RegistrationUiState.Loading -> {
            LoadingScreen()
        }

        is RegistrationUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetry = { viewModel.resetUiState() }
            )
        }

        is RegistrationUiState.Init,
        RegistrationUiState.Success -> {
            RegistrationScreenContent(
                fullName = fullName,
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                passwordVisible = passwordVisible,
                confirmPasswordVisible = confirmPasswordVisible,
                isRegisterEnabled = isRegisterEnabled,
                onFullNameChange = { fullName = it },
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onConfirmPasswordChange = { confirmPassword = it },
                onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
                onConfirmPasswordVisibilityChange = {
                    confirmPasswordVisible = !confirmPasswordVisible
                },
                onRegisterClick = {
                    viewModel.onRegisterClick(fullName, email, password)
                },
                onLoginClick = onLoginClick
            )
        }
    }
}

@Composable
fun RegistrationScreenContent(
    fullName: String,
    email: String,
    password: String,
    confirmPassword: String,
    passwordVisible: Boolean,
    confirmPasswordVisible: Boolean,
    isRegisterEnabled: Boolean,
    onFullNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onConfirmPasswordVisibilityChange: () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Registration Screen")

        OutlinedTextField(
            value = fullName,
            onValueChange = onFullNameChange,
            label = { Text("Full Name") },
            modifier = Modifier.padding(top = 12.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.padding(top = 12.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(if (passwordVisible) "Password (visible)" else "Password") },
            modifier = Modifier.padding(top = 12.dp)
        )

        TextButton(
            onClick = onPasswordVisibilityChange,
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(if (passwordVisible) "Hide Password" else "Show Password")
        }

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = {
                Text(
                    if (confirmPasswordVisible) {
                        "Confirm Password (visible)"
                    } else {
                        "Confirm Password"
                    }
                )
            },
            modifier = Modifier.padding(top = 12.dp)
        )

        TextButton(
            onClick = onConfirmPasswordVisibilityChange,
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(if (confirmPasswordVisible) "Hide Confirm Password" else "Show Confirm Password")
        }

        Button(
            onClick = onRegisterClick,
            enabled = isRegisterEnabled,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Register")
        }

        TextButton(
            onClick = onLoginClick,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Already have an account? Login")
        }
    }
}
