package com.example.myapplication.util

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Quests : Screen("quests")
    object AddQuest : Screen("add_quest")
}
