package com.example.cuidapet.ui.screens.pets.models

import com.example.cuidapet.ui.screens.pets.models.enums.MedicalRecordType
import java.time.LocalDate

data class MedicalRecord(
    val id: String,
    val type: MedicalRecordType,
    val title: String,
    val date: LocalDate,
    val clinicName: String,
    val clinicAddress: String,
    val vetName: String,
    val vetCRMV: String,
    val needsReturn: Boolean
)