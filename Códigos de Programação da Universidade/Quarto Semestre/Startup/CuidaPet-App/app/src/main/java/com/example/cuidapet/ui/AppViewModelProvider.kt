package com.example.cuidapet.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cuidapet.CuidapetApplication
import com.example.cuidapet.ui.components.NavbarViewModel
import com.example.cuidapet.ui.screens.onboarding.OnboardingViewModel
import com.example.cuidapet.ui.screens.pets.list.PetListViewModel
import com.example.cuidapet.ui.screens.pets.overview.PetOverviewViewModel
import com.example.cuidapet.ui.screens.pets.profile.PetProfileViewModel
import com.example.cuidapet.ui.screens.pets.register.PetRegisterViewModel
import com.example.cuidapet.ui.screens.user.UserViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {

        // Navbar
        initializer {
            NavbarViewModel(cuidapetApplication().petRepository)
        }

        // Onboarding
        initializer {
            OnboardingViewModel(
                userRepository = cuidapetApplication().userRepository,
                petRepository = cuidapetApplication().petRepository
            )
        }

        // User Profile
        initializer {
            UserViewModel(
                userRepository = cuidapetApplication().userRepository
            )
        }

        // Pet List
        initializer {
            PetListViewModel(
                petRepository = cuidapetApplication().petRepository
            )
        }

        // Pet Profile
        initializer {
            PetProfileViewModel(
                petRepository = cuidapetApplication().petRepository
            )
        }

        // Pet Overview
        initializer {
            PetOverviewViewModel(
                petRepository = cuidapetApplication().petRepository
            )
        }

        // Pet Register
        initializer {
            PetRegisterViewModel(
                petRepository = cuidapetApplication().petRepository
            )
        }
    }
}


fun CreationExtras.cuidapetApplication(): CuidapetApplication {
    val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
    return application as CuidapetApplication
}

