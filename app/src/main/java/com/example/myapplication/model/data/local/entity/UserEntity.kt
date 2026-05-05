package com.example.myapplication.model.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,

    @ColumnInfo(name="full_name")
    val fullName: String,

    val email: String,
    val password: String,

    @ColumnInfo(name="level_no")
    val levelNo: Int=1,

    @ColumnInfo(name="level_description")
    val levelDescription: String="Novice",

    @ColumnInfo(name="xp_total")
    val xpTotal: Int=0,

    @ColumnInfo(name="created_at")
    val createdAt: Long=System.currentTimeMillis()
)



