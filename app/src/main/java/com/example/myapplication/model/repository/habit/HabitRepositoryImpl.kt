package com.example.myapplication.model.repository.habit

import com.example.myapplication.model.datasource.network.dto.CreateHabitDto
import com.example.myapplication.model.datasource.network.dto.HabitDto
import com.example.myapplication.model.datasource.network.dto.UpdateHabitDto
import com.example.myapplication.model.datasource.network.service.HabitApiService
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val api: HabitApiService
) : HabitRepository {

    override suspend fun getHabits(): List<HabitDto> {
        return api.getHabits()
    }

    override suspend fun getHabitById(id: Int): HabitDto {
        return api.getHabitById(id)
    }

    override suspend fun createHabit(habit: CreateHabitDto): HabitDto {
        return api.createHabit(habit)
    }

    override suspend fun updateHabit(id: Int, habit: UpdateHabitDto): HabitDto {
        return api.updateHabit(id, habit)
    }

    override suspend fun deleteHabit(id: Int) {
        api.deleteHabit(id)
    }
}
