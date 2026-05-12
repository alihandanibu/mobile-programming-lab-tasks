package com.example.myapplication.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.view_model.auth.login.LoginViewModel
import com.example.myapplication.presentation.view_model.auth.registration.RegistrationViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.AddQuestScreen
import com.example.myapplication.ui.screens.habit.HabitsScreen
import com.example.myapplication.ui.screens.HomeShortcutScreen
import com.example.myapplication.ui.screens.LoginScreen
import com.example.myapplication.ui.screens.ProfileScreen
import com.example.myapplication.ui.screens.QuestsScreen
import com.example.myapplication.ui.screens.RegistrationScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(
                viewModel = hiltViewModel<LoginViewModel>(),
                onNavigate = {
                    navController.navigate(Screen.HomeShortcut.route)
                },
                onRegisterClick = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        composable(route = Screen.Register.route) {
            RegistrationScreen(
                viewModel = hiltViewModel<RegistrationViewModel>(),
                onNavigate = {
                    navController.navigate(Screen.HomeShortcut.route)
                },
                onLoginClick = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }
        composable(route = Screen.HomeShortcut.route) {
            HomeShortcutScreen(
                viewModel = hiltViewModel(),
                onScreenClick = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(route = Screen.Quest.route) {
            QuestsScreen(
                onAddQuestClick = {
                    navController.navigate(Screen.AddQuest.createRoute("quest_screen"))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.AddQuest.route,
            arguments = listOf(
                navArgument("source") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val source = backStackEntry.arguments?.getString("source").orEmpty()

            AddQuestScreen(
                source = source,
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.Habit.route) {
            HabitsScreen(
                viewModel = hiltViewModel()
            )
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(
                viewModel = hiltViewModel()
            )
        }
        }
    }
