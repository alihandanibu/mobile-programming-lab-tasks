package com.example.myapplication.model.data.remote.service

import com.example.myapplication.model.data.remote.dto.HabitDto
import retrofit2.http.GET

interface HabitApiService {

    @GET("todos")
    suspend fun getHabits(): List<HabitDto>
}