package com.example.myapplication.model.repository.example

import com.example.myapplication.model.data.local.dao.StudentCourseDao
import com.example.myapplication.model.data.local.entity.CourseEntity
import com.example.myapplication.model.data.local.entity.StudentCourseEntity
import com.example.myapplication.model.data.local.entity.StudentEntity

class StudentCourseRepositoryImpl(
   private val studentCourseDao: StudentCourseDao
) : StudentCourseRepository {

   override suspend fun insertStudent(student: StudentEntity) {
       studentCourseDao.insertStudent(student)
   }

   override suspend fun insertCourse(course: CourseEntity) {
       studentCourseDao.insertCourse(course)
   }

   override suspend fun insertStudentCourse(studentCourse: StudentCourseEntity) {
       studentCourseDao.insertStudentCourse(studentCourse)
   }

   override suspend fun getAllStudents(): List<StudentEntity> {
       return studentCourseDao.getAllStudents()
   }

   override suspend fun getAllCourses(): List<CourseEntity> {
       return studentCourseDao.getAllCourses()
   }

   override suspend fun getAllStudentCourses(): List<StudentCourseEntity> {
       return studentCourseDao.getAllStudentCourses()
   }
}
