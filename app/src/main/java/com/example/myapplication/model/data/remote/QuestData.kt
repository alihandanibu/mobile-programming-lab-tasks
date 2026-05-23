package com.example.myapplication.model.data.remote

data class QuestData(
    var id: String = "",
    var userId: String = "",
    var title: String = "",
    var xp: Int = 0,
    var category: String = "",
    var difficulty: String = "",
    var completed: Boolean = false,
    var daily: Boolean = false
)
