package com.example.cuidapet.ui.screens.pets.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuidapet.data.repository.PetRepository
import kotlinx.coroutines.launch

class PetRegisterViewModel(
    private val petRepository: PetRepository
) : ViewModel() {

    // Estado do formulário
    var uiState by mutableStateOf(PetRegisterFormState())
        private set

    // Função para atualizar o estado (digitação)
    fun updateState(newState: PetRegisterFormState) {
        uiState = newState
    }

    // Função para salvar no banco
    fun savePet(onSuccess: () -> Unit) {
        viewModelScope.launch {
            // Verifica se não tem erros antes de salvar
            if (uiState.errors.isEmpty()) {
                // Converte Form -> Pet Model
                val pet = uiState.toPet()

                // Salva no Banco
                petRepository.savePet(pet)

                // Chama o callback de sucesso (navegação)
                onSuccess()
            }
        }
    }
}