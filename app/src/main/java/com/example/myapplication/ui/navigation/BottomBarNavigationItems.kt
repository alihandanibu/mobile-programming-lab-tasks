package com.example.myapplication.ui.navigation

object BottomBarNavigationItems {
    val items = listOf(
        BottomBarNavigationItem(
            title = "Home",
            route = Screen.HomeShortcut.route
        ),
        BottomBarNavigationItem(
            title = "Quest",
            route = Screen.Quest.route
        ),
        BottomBarNavigationItem(
            title = "Habit",
            route = Screen.Habit.route
        ),
        BottomBarNavigationItem(
            title = "Profile",
            route = Screen.Profile.route
        )
    )
}