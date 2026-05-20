package com.example.myapplication.model.repository.user.mapper

import com.example.myapplication.model.data.local.entity.UserEntity

// Assuming these UI models exist or need to be created based on the lab description
data class ProfileData(
    val id: Int,
    val fullName: String,
    val email: String,
    val levelNo: Int,
    val levelDescription: String,
    val xpTotal: Int
)

data class EditProfileData(
    val id: Int,
    val fullName: String,
    val email: String
)

fun UserEntity.toProfileData(): ProfileData {
    return ProfileData(
        id = id,
        fullName = fullName,
        email = email,
        levelNo = levelNo,
        levelDescription = levelDescription,
        xpTotal = xpTotal
    )
}

fun UserEntity.toEditProfileData(): EditProfileData {
    return EditProfileData(
        id = id,
        fullName = fullName,
        email = email
    )
}

fun EditProfileData.toEntity(existingUser: UserEntity): UserEntity {
    return existingUser.copy(
        fullName = fullName,
        email = email
    )
}
