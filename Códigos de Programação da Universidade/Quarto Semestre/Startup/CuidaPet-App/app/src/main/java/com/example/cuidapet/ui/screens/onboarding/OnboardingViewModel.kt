package com.example.cuidapet.ui.screens.onboarding

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.cuidapet.ui.screens.user.components.UserValidators
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuidapet.data.repository.PetRepository
import com.example.cuidapet.data.repository.UserRepository
import com.example.cuidapet.ui.screens.pets.models.Pet
import com.example.cuidapet.ui.screens.pets.register.components.PetRegisterValidators
import com.example.cuidapet.ui.screens.user.models.User
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID


class OnboardingViewModel(
    private val userRepository: UserRepository,
    private val petRepository: PetRepository
) : ViewModel() {

    var userState by mutableStateOf(OnboardingUserState())
    var petState by mutableStateOf(OnboardingPetState())

    @RequiresApi(Build.VERSION_CODES.O)
    fun validateUserStep(): String? {
        val s = userState
        UserValidators.validateName(s.name)?.let { return it }
        UserValidators.validateBirthdate(s.birthdate)?.let { return it }
        UserValidators.validateGender(s.gender)?.let { return it }
        if (!s.acceptedPrivacy) return "Você precisa aceitar a Política de Privacidade."
        if (!s.acceptedGuidelines) return "Você precisa concordar com as Diretrizes de Uso."
        return null
    }

    fun validatePetStep(): String? {
        val s = petState
        PetRegisterValidators.validateName(s.name)?.let { return it }
        PetRegisterValidators.validateBirthdate(s.birthdate)?.let { return it }
        if (s.sex == null) return "Selecione o sexo do pet."
        if (s.species == null) return "Selecione a espécie do pet."
        if (s.isNeutered == null) return "Selecione o status de castração."
        return null
    }

    fun updateUserDate(year: Int, month: Int, day: Int) {
        userState = userState.copy(birthdate = LocalDate.of(year, month + 1, day))
    }

    fun updatePetDate(year: Int, month: Int, day: Int) {
        petState = petState.copy(birthdate = LocalDate.of(year, month + 1, day))
    }

    fun saveOnboardingData(onSuccess: () -> Unit) {
        viewModelScope.launch {
            // 1. Criar objeto User (Podemos usar !! pq a validação já passou)
            val user = User(
                id = UUID.randomUUID().toString(),
                name = userState.name,
                birthdate = userState.birthdate!!,
                gender = userState.gender!!
            )

            // 2. Criar objeto Pet
            val pet = Pet(
                id = UUID.randomUUID().toString(),
                name = petState.name,
                birthdate = petState.birthdate!!,
                sex = petState.sex!!,
                species = petState.species!!,
                isNeutered = petState.isNeutered!!,
                // Campos que não pedimos no onboarding ficam vazios
                breed = "",
                microchip = null,
                weightKg = null,
                color = "",
                chronicDiseases = emptyList(),
                allergies = emptyList()
            )

            // 3. Salvar usando os Repositórios
            userRepository.saveUser(user)
            petRepository.savePet(pet)

            // 4. Avisar a tela que terminou
            onSuccess()
        }
    }
}