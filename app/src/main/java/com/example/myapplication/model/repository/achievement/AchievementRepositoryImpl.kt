package com.example.myapplication.model.repository.achievement

import com.example.myapplication.model.data.local.dao.AchievementDao
import com.example.myapplication.model.data.local.entity.AchievementEntity
import com.example.myapplication.model.data.local.entity.UserAchievementEntity
import javax.inject.Inject

class AchievementRepositoryImpl @Inject constructor(
    private val achievementDao: AchievementDao
) : AchievementRepository {
    override suspend fun insertAchievements(achievements: List<AchievementEntity>) =
        achievementDao.insertAchievements(achievements)

    override suspend fun insertUserAchievement(userAchievement: UserAchievementEntity) =
        achievementDao.insertUserAchievement(userAchievement)

    override suspend fun getAllAchievements(): List<AchievementEntity> =
        achievementDao.getAllAchievements()

    override suspend fun getUnlockedAchievementsForUser(userId: Int): List<AchievementEntity> =
        achievementDao.getUnlockedAchievementsForUser(userId)

    override suspend fun isAchievementUnlocked(userId: Int, achievementId: Int): Boolean =
        achievementDao.isAchievementUnlocked(userId, achievementId)

    override suspend fun getUnlockedCount(userId: Int): Int =
        achievementDao.getUnlockedCount(userId)
}
