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
import com.example.myapplication.presentation.view_model.auth.login.LoginNavigationEvent
import com.example.myapplication.presentation.view_model.auth.login.LoginUiState
import com.example.myapplication.presentation.view_model.auth.login.LoginViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onNavigate: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val isEmailValid = email.isNotBlank()
    val isPasswordValid = password.isNotBlank()
    val isLoginEnabled = isEmailValid && isPasswordValid

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collectLatest { event ->
            when (event) {
                LoginNavigationEvent.Navigate -> onNavigate()
                LoginNavigationEvent.NavigateBack -> { }
            }
        }
    }

    when (val state = uiState) {
        LoginUiState.Loading -> {
            LoadingScreen()
        }

        is LoginUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetry = { viewModel.resetUiState() }
            )
        }

        is LoginUiState.Init,
        is LoginUiState.Success -> {
            LoginScreenContent(
                email = email,
                password = password,
                passwordVisible = passwordVisible,
                isLoginEnabled = isLoginEnabled,
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
                onLoginClick = { viewModel.onLoginClick(email, password) },
                onRegisterClick = onRegisterClick
            )
        }
    }
}

@Composable
fun LoginScreenContent(
    email: String,
    password: String,
    passwordVisible: Boolean,
    isLoginEnabled: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login Screen")

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            modifier = Modifier.padding(top = 16.dp)
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

        Button(
            onClick = onLoginClick,
            enabled = isLoginEnabled,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Login")
        }

        TextButton(
            onClick = onRegisterClick,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Don't have an account? Register")
        }
    }
}
