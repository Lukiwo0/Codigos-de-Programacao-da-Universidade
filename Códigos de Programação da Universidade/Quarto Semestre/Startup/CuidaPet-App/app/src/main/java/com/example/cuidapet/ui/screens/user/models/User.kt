package com.example.cuidapet.ui.screens.user.models

import java.time.LocalDate
import java.util.UUID

data class User(
    val id: String = UUID.randomUUID().toString(), // ID único para o banco
    val name: String,
    val birthdate: LocalDate,
    val gender: Gender,
)