package com.example.myapplication.model.repository.example

import com.example.myapplication.model.data.local.entity.CourseEntity
import com.example.myapplication.model.data.local.entity.StudentCourseEntity
import com.example.myapplication.model.data.local.entity.StudentEntity

interface StudentCourseRepository {

    suspend fun insertStudent(student: StudentEntity)

    suspend fun insertCourse(course: CourseEntity)

    suspend fun insertStudentCourse(studentCourse: StudentCourseEntity)

    suspend fun getAllStudents(): List<StudentEntity>

    suspend fun getAllCourses(): List<CourseEntity>

    suspend fun getAllStudentCourses(): List<StudentCourseEntity>
}
