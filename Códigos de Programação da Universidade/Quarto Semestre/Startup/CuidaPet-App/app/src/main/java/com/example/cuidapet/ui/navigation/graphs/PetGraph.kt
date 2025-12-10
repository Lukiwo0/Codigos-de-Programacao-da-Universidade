package com.example.cuidapet.ui.navigation.graphs

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.screens.onboarding.OnboardingViewModel
import com.example.cuidapet.ui.screens.pets.alerts.PetAlertDetailScreen
import com.example.cuidapet.ui.screens.pets.alerts.PetAlertRegisterScreen
import com.example.cuidapet.ui.screens.pets.alerts.PetAlertsScreen
import com.example.cuidapet.ui.screens.pets.list.PetListScreen
import com.example.cuidapet.ui.screens.pets.medicalrecords.PetMedicalRecordDetailScreen
import com.example.cuidapet.ui.screens.pets.medicalrecords.PetMedicalRecordRegisterScreen
import com.example.cuidapet.ui.screens.pets.medicalrecords.PetMedicalRecordsScreen
import com.example.cuidapet.ui.screens.pets.notes.PetNoteDetailScreen
import com.example.cuidapet.ui.screens.pets.notes.PetNoteRegisterScreen
import com.example.cuidapet.ui.screens.pets.notes.PetNotesScreen
import com.example.cuidapet.ui.screens.pets.overview.PetOverviewScreen
import com.example.cuidapet.ui.screens.pets.profile.PetProfileScreen
import com.example.cuidapet.ui.screens.pets.register.PetRegisterScreen
import com.example.cuidapet.ui.screens.pets.vaccines.PetVaccineDetailScreen
import com.example.cuidapet.ui.screens.pets.vaccines.PetVaccineRegisterScreen
import com.example.cuidapet.ui.screens.pets.vaccines.PetVaccinesScreen
import kotlinx.coroutines.CoroutineScope

fun NavGraphBuilder.petGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    onboardingViewModel: OnboardingViewModel,
    scope: CoroutineScope
) {
    navigation(
        startDestination = Destinations.PET_LIST,
        route = Destinations.PET_BASE
    ) {
        composable(Destinations.PET_LIST) {
            PetListScreen(navController)
        }

        composable(Destinations.PET_REGISTER) {
            PetRegisterScreen(navController)
        }



        composable("{$Destinations.PET_BASE}/{petId}/profile") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            PetProfileScreen(navController, snackbarHostState, scope, petId)
        }
        composable("{$Destinations.PET_BASE}/{petId}/overview") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            PetOverviewScreen(navController, snackbarHostState, scope, petId)
        }



        composable("{$Destinations.PET_BASE}/{petId}/vaccines") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            PetVaccinesScreen(navController, snackbarHostState, scope, petId)
        }
        composable("{$Destinations.PET_BASE}/{petId}/vaccines/{vaccineId}") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            val vaccineId = backStackEntry.arguments?.getString("vaccineId") ?: return@composable
            PetVaccineDetailScreen(navController, snackbarHostState, scope, petId, vaccineId)
        }
        composable("{$Destinations.PET_BASE}/{petId}/vaccines/register") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            PetVaccineRegisterScreen(navController, snackbarHostState, scope, petId)
        }



        composable("{$Destinations.PET_BASE}/{petId}/medical-records") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            PetMedicalRecordsScreen(navController, snackbarHostState, scope, petId)
        }
        composable("{$Destinations.PET_BASE}/{petId}/medical-records/{medicalRecordId}") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            val medicalRecordId = backStackEntry.arguments?.getString("medicalRecordId") ?: return@composable
            PetMedicalRecordDetailScreen(navController, snackbarHostState, scope, petId, medicalRecordId)
        }
        composable("{$Destinations.PET_BASE}/{petId}/medical-records/register") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            PetMedicalRecordRegisterScreen(navController, snackbarHostState, scope, petId)
        }



        composable("{$Destinations.PET_BASE}/{petId}/alerts") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            PetAlertsScreen(navController, snackbarHostState, scope, petId)
        }
        composable("{$Destinations.PET_BASE}/{petId}/alerts/{alertId}") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            val alertId = backStackEntry.arguments?.getString("alertId") ?: return@composable
            PetAlertDetailScreen(navController, snackbarHostState, scope, petId, alertId)
        }
        composable("{$Destinations.PET_BASE}/{petId}/alerts/register") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            PetAlertRegisterScreen(navController, snackbarHostState, scope, petId)
        }



        composable("{$Destinations.PET_BASE}/{petId}/notes") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            PetNotesScreen(navController, snackbarHostState, scope, petId)
        }
        composable("{$Destinations.PET_BASE}/{petId}/notes/{noteId}") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            val noteId = backStackEntry.arguments?.getString("noteId") ?: return@composable
            PetNoteDetailScreen(navController, snackbarHostState, scope, petId, noteId)
        }
        composable("{$Destinations.PET_BASE}/{petId}/notes/register") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId") ?: return@composable
            PetNoteRegisterScreen(navController, snackbarHostState, scope, petId)
        }

    }
}