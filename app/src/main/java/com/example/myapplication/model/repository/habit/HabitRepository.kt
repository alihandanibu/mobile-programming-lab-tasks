package com.example.myapplication.model.repository.habit

import com.example.myapplication.model.data.remote.dto.HabitDto

interface HabitRepository {
    suspend fun getHabits(): List<HabitDto>
}