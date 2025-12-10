package com.example.cuidapet.ui.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cuidapet.R
import com.example.cuidapet.ui.components.AppBottomBar
import com.example.cuidapet.ui.components.AppNavbar
import com.example.cuidapet.ui.navigation.AppNavigation
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.theme.CuidaPetTheme

@Composable
fun AppScaffold() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val hideBarsRoutes = listOf(
        Destinations.ONBOARDING,
        Destinations.ONBOARDING_INTRO,
        Destinations.ONBOARDING_MISSION,
        Destinations.ONBOARDING_FEATURES,
        Destinations.ONBOARDING_USER_DATA,
        Destinations.ONBOARDING_PET_DATA,
        Destinations.ONBOARDING_END
    )

    val showBars = currentRoute !in hideBarsRoutes

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState,
                    snackbar = { data ->
                        Snackbar(
                            snackbarData = data,
                            containerColor = colorResource(R.color.warning),
                            contentColor = colorResource(R.color.textblack)
                        )
                    }
                )
            },

            topBar = {
                if (showBars) {
                    AppNavbar(
                        currentRoute = currentRoute,
                        navController = navController,
                        snackbarHostState = snackbarHostState,
                        scope = scope,
                    )
                }
            },

            bottomBar = {}

        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                AppNavigation(
                    navController = navController,
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )

                if (showBars) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        AppBottomBar(navController = navController)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppScaffoldPreview() {
    CuidaPetTheme {
        AppScaffold()
    }
}

