package com.example.cuidapet.ui.screens.pets.common

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cuidapet.ui.screens.pets.models.Alert
import com.example.cuidapet.ui.screens.pets.models.MedicalRecord
import com.example.cuidapet.ui.screens.pets.models.Note
import com.example.cuidapet.ui.screens.pets.models.Pet
import com.example.cuidapet.ui.screens.pets.models.Vaccine
import com.example.cuidapet.ui.screens.pets.models.enums.AlertRepetition
import com.example.cuidapet.ui.screens.pets.models.enums.AlertType
import com.example.cuidapet.ui.screens.pets.models.enums.DosageType
import com.example.cuidapet.ui.screens.pets.models.enums.MedicalRecordType
import com.example.cuidapet.ui.screens.pets.models.enums.NeuteredStatus
import com.example.cuidapet.ui.screens.pets.models.enums.Sex
import com.example.cuidapet.ui.screens.pets.models.enums.Species
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

object PetItemsProvider {

    @RequiresApi(Build.VERSION_CODES.O)

    // Testar list vazia
//    val pets = listOf<Pet>()

    val pets = listOf(
        Pet(
            id = UUID.randomUUID().toString(),
            name = "Luna Fernanda Fernanda Fernanda Fernanda Fernanda Fernanda Fernanda Fernanda Fernanda Fernanda Fernanda ",
            birthdate = LocalDate.of(2021, 4, 12),
            sex = Sex.FEMALE,
            species = Species.DOG,
            breed = "Labrador Retriever",
            isNeutered = NeuteredStatus.NEUTERED,
            microchip = "985112004623589",
            weightKg = 24.5,
            color = "Yellow",
            chronicDiseases = listOf("Dermatite Atópica"),
            allergies = listOf("Frango"),
            vaccines = listOf(
                Vaccine(
                    id = UUID.randomUUID().toString(),
                    name = "V10",
                    date = LocalDate.of(2024, 2, 10),
                    dosageType = DosageType.SINGLE,
                    totalDoses = 1,
                    interval = null,
                    location = "Clínica PetCare",
                    locationAddress = "Rua das Flores, 145",
                    vetName = "Dr. Marcos Silva",
                    vetCRMV = "CRMV-SP 25412"
                )
            ),
            medicalRecords = emptyList(),
            notes = listOf(
                Note(
                    id = UUID.randomUUID().toString(),
                    title = "Banho Especial",
                    content = "Banho com shampoo hipoalergênico dia 15/01.",
                    createdAt = LocalDateTime.now().minusDays(12)
                )
            ),
            alerts = emptyList()
        ),
        Pet(
            id = UUID.randomUUID().toString(),
            name = "Thor",
            birthdate = LocalDate.of(2019, 10, 5),
            sex = Sex.MALE,
            species = Species.DOG,
            breed = "Husky Siberiano",
            isNeutered = NeuteredStatus.NOT_NEUTERED,
            microchip = null,
            weightKg = 28.0,
            color = "Black & White",
            chronicDiseases = emptyList(),
            allergies = emptyList(),
            vaccines = listOf(
                Vaccine(
                    id = UUID.randomUUID().toString(),
                    name = "Raiva",
                    date = LocalDate.of(2023, 7, 22),
                    dosageType = DosageType.YEARLY,
                    totalDoses = null,
                    interval = null,
                    location = "Pet Vida Clínica",
                    locationAddress = "Av. Paulista, 780",
                    vetName = "Dra. Helena Prado",
                    vetCRMV = "CRMV-SP 18904"
                )
            ),
            medicalRecords = listOf(
                MedicalRecord(
                    id = UUID.randomUUID().toString(),
                    type = MedicalRecordType.CONSULTATION,
                    title = "Consulta geral",
                    date = LocalDate.of(2024, 1, 18),
                    clinicName = "VetCare",
                    clinicAddress = "Rua Azul, 90",
                    vetName = "Dr. Renato Dias",
                    vetCRMV = "CRMV-SP 21589",
                    needsReturn = false
                )
            ),
            notes = emptyList(),
            alerts = listOf(
                Alert(
                    id = UUID.randomUUID().toString(),
                    name = "Vermífugo",
                    type = AlertType.MEDICATION,
                    description = "Aplicar comprimido de vermífugo",
                    dateTime = LocalDateTime.now().plusDays(10),
                    repetition = AlertRepetition.YEARLY,
                    customInterval = null,
                    durationMonths = 12
                )
            )
        ),
        Pet(
            id = UUID.randomUUID().toString(),
            name = "Mimi",
            birthdate = LocalDate.of(2022, 2, 20),
            sex = Sex.FEMALE,
            species = Species.CAT,
            breed = "Vira-lata",
            isNeutered = NeuteredStatus.NEUTERED,
            microchip = "984200147852369",
            weightKg = 4.2,
            color = "Cinza",
            chronicDiseases = listOf("Asma Felina"),
            allergies = emptyList(),
            vaccines = emptyList(),
            medicalRecords = emptyList(),
            notes = emptyList(),
            alerts = emptyList()
        )
    )
}