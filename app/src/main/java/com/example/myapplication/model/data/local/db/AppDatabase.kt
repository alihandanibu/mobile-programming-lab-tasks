package com.example.myapplication.model.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.data.local.dao.AchievementDao
import com.example.myapplication.model.data.local.dao.StudentCourseDao
import com.example.myapplication.model.data.local.dao.UserDao
import com.example.myapplication.model.data.local.entity.*

@Database(
    entities = [
        UserEntity::class,
        AchievementEntity::class,
        UserAchievementEntity::class,
        StudentEntity::class,
        CourseEntity::class,
        StudentCourseEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun achievementDao(): AchievementDao
    abstract fun studentCourseDao(): StudentCourseDao
}
