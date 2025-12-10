package com.example.cuidapet.ui.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cuidapet.data.preferences.UserPreferences
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.screens.onboarding.OnboardingScreenEnd
import com.example.cuidapet.ui.screens.onboarding.OnboardingScreenFeatures
import com.example.cuidapet.ui.screens.onboarding.OnboardingScreenIntro
import com.example.cuidapet.ui.screens.onboarding.OnboardingScreenMission
import com.example.cuidapet.ui.screens.onboarding.OnboardingScreenPetData
import com.example.cuidapet.ui.screens.onboarding.OnboardingScreenUserData
import com.example.cuidapet.ui.screens.onboarding.OnboardingViewModel

fun NavGraphBuilder.onboardingGraph(
    navController: NavController,
    userPreferences: UserPreferences,
    onboardingViewModel: OnboardingViewModel
) {
    navigation(
        startDestination = Destinations.ONBOARDING_INTRO, route = Destinations.ONBOARDING
    ) {
        composable(Destinations.ONBOARDING_INTRO) {
            OnboardingScreenIntro(
                navController, onboardingViewModel
            )
        }
        composable(Destinations.ONBOARDING_MISSION) {
            OnboardingScreenMission(
                navController, onboardingViewModel
            )
        }
        composable(Destinations.ONBOARDING_FEATURES) {
            OnboardingScreenFeatures(
                navController, onboardingViewModel
            )
        }
        composable(Destinations.ONBOARDING_USER_DATA) {
            OnboardingScreenUserData(
                navController, onboardingViewModel
            )
        }
        composable(Destinations.ONBOARDING_PET_DATA) {
            OnboardingScreenPetData(
                navController, onboardingViewModel
            )
        }
        composable(Destinations.ONBOARDING_END) {
            OnboardingScreenEnd(
                navController, userPreferences, onboardingViewModel
            )
        }
    }
}