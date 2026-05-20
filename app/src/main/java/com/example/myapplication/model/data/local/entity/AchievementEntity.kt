package com.example.myapplication.model.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    @ColumnInfo(name = "xp_bonus")
    val xpBonus: Int,
    @ColumnInfo(name = "condition_type")
    val conditionType: String
)
