package com.example.cuidapet.ui.screens.user

import com.example.cuidapet.ui.screens.user.models.Gender
import com.example.cuidapet.ui.screens.user.models.User
import java.time.LocalDate

data class UserProfileState(
    val name: String = "",
    val birthdate: LocalDate? = null,
    val gender: Gender? = null,
    val errors: Map<String, String> = emptyMap(),
    val isEditing: Boolean = false // Para controlar se mostra inputs ou textos
)


fun UserProfileState.toUser(existingId: String? = null): User {
    return User(
        id = existingId ?: java.util.UUID.randomUUID().toString(),
        name = this.name.trim(),
        birthdate = this.birthdate!!,
        gender = this.gender!!
    )
}

fun User.toState(): UserProfileState {
    return UserProfileState(
        name = this.name,
        birthdate = this.birthdate,
        gender = this.gender,
        errors = emptyMap(),
        isEditing = false
    )
}