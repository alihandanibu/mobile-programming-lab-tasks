package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.AddQuestScreen
import com.example.myapplication.ui.screens.LoginScreen
import com.example.myapplication.ui.screens.RegisterScreen
import com.example.myapplication.ui.screens.QuestsScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Login.route
                ) {
                    composable(Screen.Login.route) {
                        LoginScreen(
                            onLoginSuccess = {
                                navController.navigate(Screen.Quests.route) {
                                    popUpTo(Screen.Login.route) { inclusive = true }
                                }
                            },
                            onNavigateToRegister = {
                                navController.navigate(Screen.Register.route)
                            }
                        )
                    }

                    composable(Screen.Register.route) {
                        RegisterScreen(
                            onRegisterSuccess = {
                                navController.navigate(Screen.Quests.route) {
                                    popUpTo(Screen.Login.route) { inclusive = true }
                                }
                            },
                            onBackToLogin = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(Screen.Quests.route) {
                        QuestsScreen(
                            onAddQuestClick = {
                                navController.navigate(Screen.AddQuest.route)
                            }
                        )
                    }

                    composable(Screen.AddQuest.route) {
                        AddQuestScreen(
                            onQuestAdded = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
