package com.example.myapplication.model.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(

    @PrimaryKey(autoGenerate = true)
    val studentId: Int = 0,

    val name: String,

    val email: String
)