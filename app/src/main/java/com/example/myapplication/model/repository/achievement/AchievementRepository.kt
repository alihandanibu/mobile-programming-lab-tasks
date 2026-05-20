package com.example.myapplication.model.repository.achievement

import com.example.myapplication.model.data.local.entity.AchievementEntity
import com.example.myapplication.model.data.local.entity.UserAchievementEntity

interface AchievementRepository {
    suspend fun insertAchievements(achievements: List<AchievementEntity>)
    suspend fun insertUserAchievement(userAchievement: UserAchievementEntity)
    suspend fun getAllAchievements(): List<AchievementEntity>
    suspend fun getUnlockedAchievementsForUser(userId: Int): List<AchievementEntity>
    suspend fun isAchievementUnlocked(userId: Int, achievementId: Int): Boolean
    suspend fun getUnlockedCount(userId: Int): Int
}
