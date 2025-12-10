package com.example.cuidapet.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cuidapet.R
import com.example.cuidapet.ui.components.AppBottomBar
import com.example.cuidapet.ui.components.AppNavbar
import com.example.cuidapet.ui.components.TemplateCard
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.screens.tips.PetCareTipsScreenContent
import com.example.cuidapet.ui.theme.CuidaPetTheme

@Composable
fun HomeScreen(navController: NavHostController) {
    HomeScreenContent(
        onPetListClick = { navController.navigate(Destinations.PET_LIST) },
        onUserProfileClick = { navController.navigate(Destinations.USER_PROFILE) },
        onAllNotificationsClick = { navController.navigate(Destinations.ALL_NOTIFICATIONS) },
        onPetCareTipsClick = { navController.navigate(Destinations.PET_CARE_TIPS) },
        onSupportClick = { navController.navigate(Destinations.SUPPORT) },
        onCreditsClick = { navController.navigate(Destinations.CREDITS) },
    )
}

@Composable
fun HomeScreenContent(
    onPetListClick: () -> Unit = {},
    onUserProfileClick: () -> Unit = {},
    onAllNotificationsClick: () -> Unit = {},
    onPetCareTipsClick: () -> Unit = {},
    onSupportClick: () -> Unit = {},
    onCreditsClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 130.dp, top = 16.dp)
    ) {
        item {
            TemplateCard(
                title = stringResource(R.string.screen_home_card_title_pets),
                description = stringResource(R.string.screen_home_card_description_pets),
                onTitleClick = onPetListClick
            )
        }

        item {
            TemplateCard(
                title = stringResource(R.string.screen_home_card_title_profile),
                description = stringResource(R.string.screen_home_card_description_profile),
                onTitleClick = onUserProfileClick
            )
        }

        item {
            TemplateCard(
                title = stringResource(R.string.screen_home_card_title_notifications),
                description = stringResource(R.string.screen_home_card_description_notifications),
                onTitleClick = onAllNotificationsClick
            )
        }

        item {
            TemplateCard(
                title = stringResource(R.string.screen_home_card_title_tips),
                description = stringResource(R.string.screen_home_card_description_tips),
                onTitleClick = onPetCareTipsClick
            )
        }

        item {
            TemplateCard(
                title = stringResource(R.string.screen_home_card_title_support),
                description = stringResource(R.string.screen_home_card_description_support),
                onTitleClick = onSupportClick
            )
        }

        item {
            TemplateCard(
                title = stringResource(R.string.screen_home_card_title_credits),
                description = stringResource(R.string.screen_home_card_description_credits),
                onTitleClick = onCreditsClick
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CuidaPetTheme {

        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        Scaffold(
            topBar = { AppNavbar(currentRoute = Destinations.HOME, navController = rememberNavController(), snackbarHostState = snackbarHostState, scope = scope) },
            bottomBar = { AppBottomBar(navController = rememberNavController()) }
        ) { padding ->
            HomeScreenContent(modifier = Modifier.padding(padding))
        }
    }
}


//@Preview
//@Composable
//fun DarkThemePreviewOnboardingScreenFirst() {
//    CuidaPetTheme (darkTheme = true){
//        HomeScreenContainerOnboardingScreenFirst()
//    }
//}
