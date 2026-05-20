package com.example.myapplication.model.data.remote

data class QuestData(
    val id: String = "",
    val userId: String = "",
    val title: String = "",
    val xp: Int = 0,
    val isCompleted: Boolean = false,
    val category: String = "",
    val difficulty: String = "",
    val isDaily: Boolean = false
)