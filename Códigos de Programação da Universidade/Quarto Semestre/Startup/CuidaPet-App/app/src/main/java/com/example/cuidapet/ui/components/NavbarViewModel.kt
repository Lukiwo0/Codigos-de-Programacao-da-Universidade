package com.example.cuidapet.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuidapet.data.repository.PetRepository
import kotlinx.coroutines.launch

class NavbarViewModel(
    private val petRepository: PetRepository
) : ViewModel() {

    var currentPetName by mutableStateOf("Carregando...")
        private set

    // Cache simples para não buscar o mesmo ID toda hora
    private var lastPetId: String? = null

    fun loadPetName(petId: String?) {
        if (petId == null) {
            currentPetName = ""
            lastPetId = null
            return
        }

        if (petId == lastPetId) return // Já carregado

        viewModelScope.launch {
            val pet = petRepository.getPetById(petId)
            // Pega só o primeiro nome
            currentPetName = pet?.name?.substringBefore(" ") ?: "Pet"
            lastPetId = petId
        }
    }
}