package com.example.cuidapet.ui.screens.pets.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuidapet.data.repository.PetRepository
import com.example.cuidapet.ui.screens.pets.models.Pet
import kotlinx.coroutines.launch

class PetProfileViewModel(
    private val petRepository: PetRepository
) : ViewModel() {

    // Estado que guarda o Pet carregado. Começa nulo.
    var pet by mutableStateOf<Pet?>(null)
        private set

    fun loadPet(petId: String) {
        viewModelScope.launch {
            // Busca no banco de dados (suspend function)
            pet = petRepository.getPetById(petId)
        }
    }
}