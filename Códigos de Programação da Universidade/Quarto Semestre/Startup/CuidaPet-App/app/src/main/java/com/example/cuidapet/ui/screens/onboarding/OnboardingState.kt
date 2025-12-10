package com.example.cuidapet.ui.screens.onboarding

import com.example.cuidapet.ui.screens.pets.models.enums.NeuteredStatus
import com.example.cuidapet.ui.screens.pets.models.enums.Sex
import com.example.cuidapet.ui.screens.pets.models.enums.Species
import com.example.cuidapet.ui.screens.user.models.Gender
import java.time.LocalDate

data class OnboardingUserState(
    val name: String = "",
    val birthdate: LocalDate? = null,
    val gender: Gender? = null,
    val acceptedPrivacy: Boolean = false,
    val acceptedGuidelines: Boolean = false
)

data class OnboardingPetState(
    val name: String = "",
    val birthdate: LocalDate? = null,
    val sex: Sex? = null,
    val species: Species? = null,
    val isNeutered: NeuteredStatus? = null
)