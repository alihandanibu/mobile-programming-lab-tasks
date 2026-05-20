package com.example.myapplication.model.data.local.util

import com.example.myapplication.model.data.local.entity.AchievementEntity

object AchievementSeed {
    val predefinedAchievements = listOf(
        AchievementEntity(
            title = "First Step",
            description = "Complete your first task",
            xpBonus = 100,
            conditionType = "FIRST_TASK"
        ),
        AchievementEntity(
            title = "Habit Master",
            description = "Maintain a 7-day streak",
            xpBonus = 500,
            conditionType = "STREAK_7"
        ),
        AchievementEntity(
            title = "Early Bird",
            description = "Complete a task before 8 AM",
            xpBonus = 200,
            conditionType = "EARLY_BIRD"
        )
    )
}
