package com.example.cuidapet.ui.screens.pets.models

import java.time.LocalDateTime

data class Note(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime
)