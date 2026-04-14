package com.example.myapplication.ui.navigation

sealed class Screen(val route: String) {

    data object Register : Screen("register_screen")
    data object Login : Screen("login_screen")
    data object HomeShortcut : Screen("home_shortcut_screen")
    data object Quest : Screen("quest_screen")
    data object Habit : Screen("habit_screen")
    data object Profile : Screen("profile_screen")

    data object AddQuest : Screen("add_quest_screen/{source}") {
        fun createRoute(source: String): String {
            return "add_quest_screen/$source"
        }
    }

    companion object {
        fun bottomNavRoutes(): List<String> {
            return listOf(
                HomeShortcut.route,
                Quest.route,
                Habit.route,
                Profile.route
            )
        }
    }
}