package com.example.myapplication.model.data.local.util

import com.example.myapplication.model.data.local.entity.UserAchievementEntity

object UserAchievementSeed {
    fun getSeedUserAchievements(userId: Int): List<UserAchievementEntity> {
        return listOf(
            UserAchievementEntity(userId = userId, achievementId = 1),
            UserAchievementEntity(userId = userId, achievementId = 2)
        )
    }
}
