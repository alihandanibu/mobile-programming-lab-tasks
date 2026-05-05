package com.example.myapplication.model.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.model.data.local.dao.UserDao
import com.example.myapplication.model.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.myapplication.model.repository.user.UserRepository
import com.example.myapplication.model.repository.user.UserRepositoryImpl
import dagger.Binds

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "mobile_lab_database"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao{
        return database.userDao()
    }
}
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}