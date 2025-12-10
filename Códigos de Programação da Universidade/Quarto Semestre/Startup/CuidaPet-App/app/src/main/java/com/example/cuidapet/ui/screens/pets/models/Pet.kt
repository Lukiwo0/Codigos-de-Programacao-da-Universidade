package com.example.cuidapet.ui.screens.pets.models

import com.example.cuidapet.ui.screens.pets.models.enums.NeuteredStatus
import com.example.cuidapet.ui.screens.pets.models.enums.Sex
import com.example.cuidapet.ui.screens.pets.models.enums.Species
import java.time.LocalDate

data class Pet(
    val id: String,
    val name: String,
    val birthdate: LocalDate,
    val sex: Sex,
    val species: Species,
    val breed: String,
    val isNeutered: NeuteredStatus,
    val microchip: String?,
    val weightKg: Double?,
    val color: String,
    val chronicDiseases: List<String>,
    val allergies: List<String>,
    val vaccines: List<Vaccine> = emptyList(),
    val medicalRecords: List<MedicalRecord> = emptyList(),
    val notes: List<Note> = emptyList(),
    val alerts: List<Alert> = emptyList()
)
