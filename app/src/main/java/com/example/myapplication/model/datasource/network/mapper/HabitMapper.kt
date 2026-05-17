package com.example.myapplication.model.datasource.network.mapper

import com.example.myapplication.model.datasource.network.dto.HabitDto
import com.example.myapplication.ui.screens.HabitModel

fun HabitDto.toDomain(): HabitModel {
    return HabitModel(
        id = this.id,
        title = this.title,
        streak = this.frequency
    )
}
