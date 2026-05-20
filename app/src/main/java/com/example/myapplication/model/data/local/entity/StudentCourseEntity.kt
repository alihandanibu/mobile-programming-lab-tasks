package com.example.myapplication.model.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "student_courses",
    primaryKeys = ["studentId", "courseId"]
)
data class StudentCourseEntity(

    val studentId: Int,

    val courseId: Int
)