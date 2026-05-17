package com.example.myapplication.model.datasource.network.dto

data class HabitDto(
    val id: Int,
    val title: String,
    val frequency: Int
)

data class CreateHabitDto(
    val title: String,
    val frequency: Int
)

data class UpdateHabitDto(
    val title: String? = null,
    val frequency: Int? = null
)
