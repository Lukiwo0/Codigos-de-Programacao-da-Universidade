package com.example.cuidapet.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cuidapet.ui.screens.pets.models.Pet
import com.example.cuidapet.ui.screens.pets.models.enums.NeuteredStatus
import com.example.cuidapet.ui.screens.pets.models.enums.Sex
import com.example.cuidapet.ui.screens.pets.models.enums.Species
import java.time.LocalDate


@Entity(tableName = "pets")
data class PetEntity(
    @PrimaryKey val id: String,
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
    val allergies: List<String>
)


fun PetEntity.toExternalModel(): Pet {
    return Pet(
        id = id,
        name = name,
        birthdate = birthdate,
        sex = sex,
        species = species,
        breed = breed,
        isNeutered = isNeutered,
        microchip = microchip,
        weightKg = weightKg,
        color = color,
        chronicDiseases = chronicDiseases,
        allergies = allergies
    )
}

fun Pet.toEntity(): PetEntity {
    return PetEntity(
        id = id,
        name = name,
        birthdate = birthdate,
        sex = sex,
        species = species,
        breed = breed,
        isNeutered = isNeutered,
        microchip = microchip,
        weightKg = weightKg,
        color = color,
        chronicDiseases = chronicDiseases,
        allergies = allergies
    )
}