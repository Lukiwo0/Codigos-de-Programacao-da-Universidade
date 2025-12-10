package com.example.cuidapet.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.cuidapet.data.preferences.UserPreferences
import com.example.cuidapet.ui.AppViewModelProvider
import com.example.cuidapet.ui.navigation.graphs.mainGraph
import com.example.cuidapet.ui.navigation.graphs.onboardingGraph
import com.example.cuidapet.ui.navigation.graphs.petGraph
import com.example.cuidapet.ui.screens.onboarding.OnboardingViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun AppNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {

    val context = LocalContext.current
    val userPreferences = UserPreferences(context)

    val onboardingDoneFlow = userPreferences.onboardingDone
    val onboardingDoneState = onboardingDoneFlow.collectAsState(initial = null)
    var onboardingDone = onboardingDoneState.value

    val onboardingViewModel: OnboardingViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )

    if (onboardingDone != null) {
        NavHost(
            navController = navController,
            startDestination = if (onboardingDone) Destinations.MAIN else Destinations.ONBOARDING
        ) {
            onboardingGraph(
                navController = navController,
                userPreferences = userPreferences,
                onboardingViewModel = onboardingViewModel
            )

            mainGraph(
                navController = navController,
                snackbarHostState = snackbarHostState,
                onboardingViewModel = onboardingViewModel,
                scope = scope
            )

            petGraph(
                navController = navController,
                snackbarHostState = snackbarHostState,
                onboardingViewModel = onboardingViewModel,
                scope = scope
            )

        }
    } else {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}