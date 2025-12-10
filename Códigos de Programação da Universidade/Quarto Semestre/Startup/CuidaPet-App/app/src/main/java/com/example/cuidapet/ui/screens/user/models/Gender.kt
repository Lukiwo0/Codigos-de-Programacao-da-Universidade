package com.example.cuidapet.ui.screens.user.models

enum class Gender(val label: String) {
    MALE("Masculino"),
    FEMALE("Feminino"),
    OTHER("Outro");

    companion object {
        fun fromLabel(label: String): Gender? {
            return entries.find { it.label == label }
        }
    }
}