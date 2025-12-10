package com.example.cuidapet.ui.screens.pets.models

import com.example.cuidapet.ui.screens.pets.models.enums.DosageType
import java.time.LocalDate

data class Vaccine(
    val id: String,
    val name: String,
    val date: LocalDate,
    val dosageType: DosageType,
    val totalDoses: Int?,
    val interval: String?,
    val location: String,
    val locationAddress: String,
    val vetName: String,
    val vetCRMV: String
)