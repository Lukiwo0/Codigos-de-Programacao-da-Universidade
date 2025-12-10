package com.example.cuidapet.ui.screens.pets.register

import com.example.cuidapet.ui.screens.pets.models.Pet
import com.example.cuidapet.ui.screens.pets.models.enums.NeuteredStatus
import com.example.cuidapet.ui.screens.pets.models.enums.Sex
import com.example.cuidapet.ui.screens.pets.models.enums.Species
import java.time.LocalDate

data class PetRegisterFormState(
    val name: String = "",
    val birthdate: LocalDate? = null,
    val sex: Sex? = null,
    val species: Species? = null,
    val breed: String = "",
    val isNeutered: NeuteredStatus? = null,
    val microchip: String = "",
    val weightKg: String = "",
    val color: String = "",
    val chronicDiseases: String = "",
    val allergies: String = "",
    val errors: Map<String, String> = emptyMap()
)

fun PetRegisterFormState.toPet(): Pet {
    return Pet(
        id = java.util.UUID.randomUUID().toString(),
        name = name,
        birthdate = birthdate!!,
        sex = sex!!,
        species = species!!,
        breed = breed,
        isNeutered = isNeutered!!,
        microchip = microchip.ifBlank { null },
        weightKg = weightKg.toDoubleOrNull(),
        color = color,
        chronicDiseases = chronicDiseases
            .split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() },
        allergies = allergies
            .split(",")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
    )
}

