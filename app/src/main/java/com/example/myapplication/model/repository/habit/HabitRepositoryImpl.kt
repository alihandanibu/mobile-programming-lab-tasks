package com.example.myapplication.model.repository.habit

import com.example.myapplication.model.data.remote.dto.HabitDto
import com.example.myapplication.model.data.remote.service.HabitApiService
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val habitApiService: HabitApiService
) : HabitRepository {

    override suspend fun getHabits(): List<HabitDto> {
        return habitApiService.getHabits()
    }
}