package com.example.cuidapet.ui.navigation.graphs

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.screens.home.AllNotificationsScreen
import com.example.cuidapet.ui.screens.home.CreditsScreen
import com.example.cuidapet.ui.screens.home.HomeScreen
import com.example.cuidapet.ui.screens.tips.PetCareTipsScreen
import com.example.cuidapet.ui.screens.tips.PetTipDetailScreen
import com.example.cuidapet.ui.screens.tips.PetTypeTipsScreen
import com.example.cuidapet.ui.screens.home.SupportScreen
import com.example.cuidapet.ui.screens.onboarding.OnboardingViewModel
import com.example.cuidapet.ui.screens.user.UserProfileScreen
import kotlinx.coroutines.CoroutineScope

fun NavGraphBuilder.mainGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    onboardingViewModel: OnboardingViewModel,
    scope: CoroutineScope
) {
    navigation(
        startDestination = Destinations.HOME,
        route = Destinations.MAIN
    ) {
        composable(Destinations.HOME) {
            HomeScreen(navController)
        }
        composable(Destinations.USER_PROFILE) {
            UserProfileScreen(navController, snackbarHostState, scope)
        }
        composable(Destinations.ALL_NOTIFICATIONS) {
            AllNotificationsScreen(navController)
        }


        composable(Destinations.PET_CARE_TIPS) {
            PetCareTipsScreen(navController, snackbarHostState, scope)
        }

        composable("${Destinations.PET_TYPE_TIPS}/{petType}") { backStackEntry ->
            val petType = backStackEntry.arguments?.getString("petType") ?: ""
            PetTypeTipsScreen(navController, petType)
        }

        composable("${Destinations.PET_TIP_DETAIL}/{petType}/{petCategory}") { backStackEntry ->
            val petType = backStackEntry.arguments?.getString("petType") ?: ""
            val petCategory = backStackEntry.arguments?.getString("petCategory") ?: ""
            PetTipDetailScreen(navController, petType, petCategory)
        }


        composable(Destinations.SUPPORT) {
            SupportScreen(navController)
        }
        composable(Destinations.CREDITS) {
            CreditsScreen(navController)
        }
    }
}