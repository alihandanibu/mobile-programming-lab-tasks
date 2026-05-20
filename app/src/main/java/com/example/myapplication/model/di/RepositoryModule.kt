package com.example.myapplication.model.di

import com.example.myapplication.model.repository.auth.AuthRepository
import com.example.myapplication.model.repository.auth.AuthRepositoryImpl
import com.example.myapplication.model.repository.quest.QuestRepository
import com.example.myapplication.model.repository.quest.QuestRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindQuestRepository(
        impl: QuestRepositoryImpl
    ): QuestRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository
}