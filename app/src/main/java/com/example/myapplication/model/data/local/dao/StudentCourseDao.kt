package com.example.myapplication.model.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.model.data.local.entity.CourseEntity
import com.example.myapplication.model.data.local.entity.StudentCourseEntity
import com.example.myapplication.model.data.local.entity.StudentEntity

@Dao
interface StudentCourseDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertStudent(student: StudentEntity)

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCourse(course: CourseEntity)

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertStudentCourse(studentCourse: StudentCourseEntity)

   @Query("SELECT * FROM students")
   suspend fun getAllStudents(): List<StudentEntity>

   @Query("SELECT * FROM courses")
   suspend fun getAllCourses(): List<CourseEntity>

   @Query("SELECT * FROM student_courses")
   suspend fun getAllStudentCourses(): List<StudentCourseEntity>
}
