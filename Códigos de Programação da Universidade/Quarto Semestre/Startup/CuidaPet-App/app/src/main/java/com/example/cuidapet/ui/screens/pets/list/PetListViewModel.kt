package com.example.cuidapet.ui.screens.pets.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuidapet.data.repository.PetRepository
import com.example.cuidapet.ui.screens.pets.models.Pet
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class PetListViewModel(
    petRepository: PetRepository
) : ViewModel() {

    val pets: StateFlow<List<Pet>> = petRepository.getAllPets()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}