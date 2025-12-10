package com.example.cuidapet.ui.screens.pets.register.components

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cuidapet.ui.screens.pets.models.enums.NeuteredStatus
import com.example.cuidapet.ui.screens.pets.models.enums.Sex
import com.example.cuidapet.ui.screens.pets.models.enums.Species
import com.example.cuidapet.ui.screens.pets.register.PetRegisterFormState
import java.time.LocalDate

object PetRegisterValidators {

    fun validateAll(state: PetRegisterFormState): Map<String, String> {
        val errors = mutableMapOf<String, String>()

        validateName(state.name)?.let { errors["name"] = it }
        validateBirthdate(state.birthdate)?.let { errors["birthdate"] = it }
        validateSex(state.sex)?.let { errors["sex"] = it }
        validateSpecies(state.species)?.let { errors["species"] = it }
        validateIsNeutered(state.isNeutered)?.let { errors["isNeutered"] = it }
        validateWeight(state.weightKg)?.let { errors["weightKg"] = it }
        validateBreed(state.breed)?.let { errors["breed"] = it }
        validateMicrochip(state.microchip)?.let { errors["microchip"] = it }

        return errors
    }

    fun validateName(name: String): String? {
        val n = name.trim()
        return when {
            n.isBlank() -> "O nome é obrigatório."
            n.length < 2 -> "O nome deve ter pelo menos 2 caracteres."
            n.length > 100 -> "O nome deve ter menos de 100 caracteres."
            n.any { it.isDigit() } -> "O nome não pode conter números."
            else -> null
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun validateBirthdate(date: LocalDate?): String? {
        if (date == null) return "A data de nascimento é obrigatória."
        if (date.isAfter(LocalDate.now())) return "A data não pode ser no futuro."
        return null
    }

    fun validateSex(sex: Sex?): String? {
        return if (sex == null) "Selecione o sexo." else null
    }

    fun validateSpecies(species: Species?): String? {
        return if (species == null) "Selecione a espécie." else null
    }

    fun validateIsNeutered(status: NeuteredStatus?): String? {
        return if (status == null) "Selecione o status de castração." else null
    }

    fun validateWeight(weightRaw: String): String? {
        val weight = weightRaw.trim()
        if (weight.isBlank()) return null // Opcional

        val value = weight.toDoubleOrNull()
        return when {
            value == null -> "Peso deve ser um número válido."
            value <= 0 -> "Peso deve ser maior que zero."
            value > 200 -> "Peso inválido para um animal."
            else -> null
        }
    }

    fun validateBreed(breed: String): String? {
        val b = breed.trim()
        if (b.isBlank()) return null
        if (b.length < 2) return "A raça deve ter pelo menos 2 caracteres."
        if (b.length > 100) return "A raça deve ter menos de 100 caracteres."
        return null
    }

    fun validateMicrochip(chip: String): String? {
        val c = chip.trim()
        if (c.isBlank()) return null
        if (!c.all { it.isDigit() }) return "O microchip deve conter apenas números."
        if (c.length !in 9..20) return "O microchip deve ter entre 9 e 20 dígitos."
        return null
    }
}
