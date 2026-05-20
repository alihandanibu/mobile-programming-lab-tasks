package com.example.myapplication.model.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.model.data.local.dao.AchievementDao
import com.example.myapplication.model.data.local.dao.StudentCourseDao
import com.example.myapplication.model.data.local.dao.UserDao
import com.example.myapplication.model.data.local.db.AppDatabase
import com.example.myapplication.model.repository.achievement.AchievementRepository
import com.example.myapplication.model.repository.achievement.AchievementRepositoryImpl
import com.example.myapplication.model.repository.user.SessionRepository
import com.example.myapplication.model.repository.user.SessionRepositoryImpl
import com.example.myapplication.model.repository.user.UserRepository
import com.example.myapplication.model.repository.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    fun provideAchievementDao(database: AppDatabase): AchievementDao = database.achievementDao()

    @Provides
    fun provideStudentCourseDao(database: AppDatabase): StudentCourseDao = database.studentCourseDao()

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideAchievementRepository(achievementDao: AchievementDao): AchievementRepository {
        return AchievementRepositoryImpl(achievementDao)
    }

    @Provides
    @Singleton
    fun provideSessionRepository(@ApplicationContext context: Context): SessionRepository {
        return SessionRepositoryImpl(context)
    }
}
