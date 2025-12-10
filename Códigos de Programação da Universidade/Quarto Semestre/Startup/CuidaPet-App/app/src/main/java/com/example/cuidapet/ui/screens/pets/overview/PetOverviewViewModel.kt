package com.example.cuidapet.ui.screens.pets.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuidapet.data.repository.PetRepository
import com.example.cuidapet.ui.screens.pets.models.Pet
import kotlinx.coroutines.launch

class PetOverviewViewModel(
    private val petRepository: PetRepository
) : ViewModel() {

    var pet by mutableStateOf<Pet?>(null)
        private set

    fun loadPet(petId: String) {
        viewModelScope.launch {
            pet = petRepository.getPetById(petId)
        }
    }
}