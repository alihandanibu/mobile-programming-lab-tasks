package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.AddQuestScreen
import com.example.myapplication.ui.screens.LoginScreen
import com.example.myapplication.ui.screens.QuestsScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
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
                    startDestination = "login"
                ) {
                    composable("login") {
                        LoginScreen(
                            onLoginSuccess = {
                                navController.navigate("quests") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("quests") {
                        QuestsScreen(
                            onAddQuestClick = {
                                navController.navigate("add_quest")
                            }
                        )
                    }

                    composable("add_quest") {
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
