package com.example.cuidapet.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cuidapet.ui.screens.user.models.Gender
import com.example.cuidapet.ui.screens.user.models.User
import java.time.LocalDate

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val birthdate: LocalDate,
    val gender: Gender
)

fun UserEntity.toExternalModel(): User {
    return User(id, name, birthdate, gender)
}

fun User.toEntity(): UserEntity {
    return UserEntity(id, name, birthdate, gender)
}