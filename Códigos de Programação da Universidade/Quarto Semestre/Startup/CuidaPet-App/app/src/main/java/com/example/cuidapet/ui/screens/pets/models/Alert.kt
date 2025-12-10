package com.example.cuidapet.ui.screens.pets.models

import com.example.cuidapet.ui.screens.pets.models.enums.AlertRepetition
import com.example.cuidapet.ui.screens.pets.models.enums.AlertType
import java.time.LocalDateTime
import kotlin.time.Duration

data class Alert(
    val id: String,
    val name: String,
    val type: AlertType,
    val description: String?,
    val dateTime: LocalDateTime,
    val repetition: AlertRepetition,
    val customInterval: Duration?,
    val durationMonths: Int
)