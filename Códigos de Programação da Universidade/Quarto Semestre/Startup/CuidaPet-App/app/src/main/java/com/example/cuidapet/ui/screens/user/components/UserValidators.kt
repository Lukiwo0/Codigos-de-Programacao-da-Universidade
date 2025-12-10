package com.example.cuidapet.ui.screens.user.components

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cuidapet.ui.screens.user.models.Gender
import java.time.LocalDate
import java.time.Period

object UserValidators {

    fun validateName(name: String): String? {
        val n = name.trim()
        return when {
            n.isBlank() -> "Por favor, preencha seu nome."
            n.length < 3 -> "O nome precisa ter pelo menos 3 caracteres."
            n.length > 100 -> "O nome precisa ter menos de 100 caracteres."
            n.any { it.isDigit() } -> "O nome não pode conter números."
            else -> null
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun validateBirthdate(date: LocalDate?): String? {
        if (date == null) return "Data de nascimento obrigatória."

        if (date.isAfter(LocalDate.now())) return "Data inválida (futuro)."

        // Regra +18
        val age = Period.between(date, LocalDate.now()).years
        if (age < 18) return "Você precisa ter pelo menos 18 anos."

        return null
    }

    fun validateGender(gender: Gender?): String? {
        return if (gender == null) "Selecione o gênero." else null
    }

}

