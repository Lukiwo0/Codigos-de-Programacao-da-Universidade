package com.example.cuidapet.ui.screens.pets.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cuidapet.R
import com.example.cuidapet.ui.AppViewModelProvider
import com.example.cuidapet.ui.components.SnackbarUtils
import com.example.cuidapet.ui.components.TemplateCard
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.screens.pets.common.PetItemsProvider
import com.example.cuidapet.ui.screens.pets.models.Pet
import com.example.cuidapet.ui.screens.user.UserProfileScreenContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PetProfileScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    petId: String,
    viewModel: PetProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    LaunchedEffect(petId) {
        viewModel.loadPet(petId)
    }

    val pet = viewModel.pet

    if (pet == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        PetProfileScreenContent(
            pet = pet,
            navController = navController,
            snackbarHostState = snackbarHostState,
            scope = scope,
            onPetOverviewClick = { id ->
                navController.navigate(Destinations.petOverviewRoute(id))
            }
        )
    }
}


@Composable
fun PetProfileScreenContent(
    pet: Pet,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    onPetOverviewClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val comingSoonText = stringResource(R.string.snackbar_coming_soon_text)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 130.dp, top = 16.dp)
    ) {

        item {
            TemplateCard(
                title = stringResource(R.string.screen_pet_profile_card_title_overview),
                description = stringResource(R.string.screen_pet_profile_card_description_overview),
                onTitleClick = { onPetOverviewClick(pet.id) }
            )
        }

        item {
            TemplateCard(
                title = stringResource(R.string.screen_pet_profile_card_title_vaccines),
                description = stringResource(R.string.screen_pet_profile_card_description_vaccines),
                onTitleClick = {},
                comingSoon = true,
                onComingSoonClick = {
                    scope.launch {
                        if (snackbarHostState.currentSnackbarData == null) {
                            SnackbarUtils.show(
                                snackbarHostState,
                                message = comingSoonText
                            )
                        }
                    }
                }

            )
        }

        item {
            TemplateCard(
                title = stringResource(R.string.screen_pet_profile_card_title_medicalrecords),
                description = stringResource(R.string.screen_pet_profile_card_description_medicalrecords),
                onTitleClick = {},
                comingSoon = true,
                onComingSoonClick = {
                    scope.launch {
                        if (snackbarHostState.currentSnackbarData == null) {
                            SnackbarUtils.show(
                                snackbarHostState,
                                message = comingSoonText
                            )
                        }
                    }
                }

            )
        }

        item {
            TemplateCard(
                title = stringResource(R.string.screen_pet_profile_card_title_alerts),
                description = stringResource(R.string.screen_pet_profile_card_description_alerts),
                onTitleClick = {},
                comingSoon = true,
                onComingSoonClick = {
                    scope.launch {
                        if (snackbarHostState.currentSnackbarData == null) {
                            SnackbarUtils.show(
                                snackbarHostState,
                                message = comingSoonText
                            )
                        }
                    }
                }

            )
        }

        item {
            TemplateCard(
                title = stringResource(R.string.screen_pet_profile_card_title_notes),
                description = stringResource(R.string.screen_pet_profile_card_description_notes),
                onTitleClick = {},
                comingSoon = true,
                onComingSoonClick = {
                    scope.launch {
                        if (snackbarHostState.currentSnackbarData == null) {
                            SnackbarUtils.show(
                                snackbarHostState,
                                message = comingSoonText
                            )
                        }
                    }
                }

            )
        }

        item {
            TemplateCard(
                title = stringResource(R.string.screen_pet_profile_card_title_gallery),
                description = stringResource(R.string.screen_pet_profile_card_description_gallery),
                onTitleClick = {},
                comingSoon = true,
                onComingSoonClick = {
                    scope.launch {
                        if (snackbarHostState.currentSnackbarData == null) {
                            SnackbarUtils.show(
                                snackbarHostState,
                                message = comingSoonText
                            )
                        }
                    }
                }

            )
        }

        item {
            TemplateCard(
                title = stringResource(R.string.screen_pet_profile_card_title_tips),
                description = stringResource(R.string.screen_pet_profile_card_description_tips),
                onTitleClick = {},
                comingSoon = true,
                onComingSoonClick = {
                    scope.launch {
                        if (snackbarHostState.currentSnackbarData == null) {
                            SnackbarUtils.show(
                                snackbarHostState,
                                message = comingSoonText
                            )
                        }
                    }
                }

            )
        }

    }
}
