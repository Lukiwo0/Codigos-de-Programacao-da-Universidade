package com.example.cuidapet.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cuidapet.R
import com.example.cuidapet.ui.AppViewModelProvider
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.navigation.Destinations.PET_LIST
import com.example.cuidapet.ui.screens.pets.common.PetItemsProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavbar(
    currentRoute: String?,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    viewModel: NavbarViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val backStackEntry = navController.currentBackStackEntryAsState().value
    val args = backStackEntry?.arguments

    val rawRoute = currentRoute ?: ""

    val petId: String? = args?.getString("petId")
        ?: if (rawRoute.contains("pets/")) {
            val parts = rawRoute.split("/")
            val index = parts.indexOf("pets")
            if (index != -1 && index + 1 < parts.size) parts[index + 1] else null
        } else null

    LaunchedEffect(petId) {
        viewModel.loadPetName(petId)
    }

    val petFirstName = viewModel.currentPetName

    val route = if (petId != null) {
        rawRoute.replace("{petId}", petId)
    } else rawRoute

    val parts = route.split("/")
    val section = parts.lastOrNull()
    val parentSection = parts.getOrNull(parts.size - 2)


    val isPetRoute = petId != null

    val petTipsRoutes = listOf(
        Destinations.PET_CARE_TIPS,
        Destinations.PET_TYPE_TIPS,
        Destinations.PET_TIP_DETAIL
    )

    val petIgnoreRoutes = listOf(
        Destinations.PET_LIST,
        Destinations.PET_REGISTER,
    )

    val comingSoonText = stringResource(R.string.snackbar_coming_soon_text)

    CenterAlignedTopAppBar(

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),

        title = {
            val titleText = when {

                isPetRoute && section == "profile" ->
                    stringResource(R.string.screen_pet_profile_navbar_title, petFirstName)

                isPetRoute && section == "overview" ->
                    stringResource(R.string.screen_pet_overview_navbar_title, petFirstName)


                isPetRoute && section == "vaccines" ->
                    stringResource(R.string.screen_pet_vaccines_navbar_title, petFirstName)

                isPetRoute && parentSection == "vaccines" && section == "register" ->
                    stringResource(R.string.screen_pet_vaccine_register_navbar_title)

                isPetRoute && parentSection == "vaccines" ->
                    stringResource(R.string.screen_pet_vaccine_detail_navbar_title)


                isPetRoute && section == "notes" ->
                    stringResource(R.string.screen_pet_notes_navbar_title, petFirstName)

                isPetRoute && parentSection == "notes" && section == "register" ->
                    stringResource(R.string.screen_pet_note_register_navbar_title)

                isPetRoute && parentSection == "notes" ->
                    stringResource(R.string.screen_pet_note_detail_navbar_title)


                isPetRoute && section == "medical-records" ->
                    stringResource(R.string.screen_pet_medicalrecords_navbar_title, petFirstName)

                isPetRoute && parentSection == "medical-records" && section == "register" ->
                    stringResource(R.string.screen_pet_medicalrecord_register_navbar_title)

                isPetRoute && parentSection == "medical-records" ->
                    stringResource(R.string.screen_pet_medicalrecord_detail_navbar_title)


                isPetRoute && section == "alerts" ->
                    stringResource(R.string.screen_pet_alerts_navbar_title, petFirstName)

                isPetRoute && parentSection == "alerts" && section == "register" ->
                    stringResource(R.string.screen_pet_alert_register_navbar_title)

                isPetRoute && parentSection == "alerts" ->
                    stringResource(R.string.screen_pet_alert_detail_navbar_title)


                currentRoute?.substringBefore("/") in petTipsRoutes -> stringResource(R.string.screen_petcaretips_navbar_title)

                else -> when (route) {

                    Destinations.HOME -> stringResource(R.string.screen_home_navbar_title)
                    Destinations.PET_LIST -> stringResource(R.string.screen_petlist_navbar_title)
                    Destinations.PET_REGISTER -> stringResource(R.string.screen_pet_register_navbar_title)
                    Destinations.USER_PROFILE -> stringResource(R.string.screen_userprofile_navbar_title)
                    Destinations.ALL_NOTIFICATIONS -> stringResource(R.string.screen_allnotifications_navbar_title)


                    Destinations.SUPPORT -> stringResource(R.string.screen_support_navbar_title)
                    Destinations.CREDITS -> stringResource(R.string.screen_credits_navbar_title)

                    else -> "Tela sem título"
                }
            }

            val titleStyle =
                if (currentRoute?.substringBefore("/") in petTipsRoutes || isPetRoute && currentRoute !in petIgnoreRoutes) {
                    MaterialTheme.typography.displaySmall.copy(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.5f),
                            offset = Offset(x = 4f, y = 4f),
                            blurRadius = 8f
                        )
                    )
                } else {
                    MaterialTheme.typography.displayMedium.copy(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.5f),
                            offset = Offset(x = 4f, y = 4f),
                            blurRadius = 8f
                        )
                    )
                }

            Text(
                text = titleText,
                style = titleStyle,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        },

        navigationIcon = {
            if (currentRoute == Destinations.HOME) {
                IconButton(onClick = {
                    scope.launch {
                        if (snackbarHostState.currentSnackbarData == null) {
                            SnackbarUtils.show(
                                snackbarHostState, message = comingSoonText
                            )
                        }
                    }
                }) {
                    Icon(
                        Icons.Rounded.Menu,
                        contentDescription = "Menu lateral",
                        modifier = Modifier
                            .size(36.dp)
                            .padding(start = 4.dp)
                    )
                }
            } else {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Voltar",
                        modifier = Modifier
                            .size(36.dp)
                            .padding(start = 4.dp)
                    )
                }
            }
        },

        actions = {
            if (currentRoute == Destinations.HOME) {
                IconButton(onClick = { navController.navigate(Destinations.ALL_NOTIFICATIONS) }) {
                    Icon(
                        Icons.Rounded.Notifications,
                        contentDescription = "Notificações",
                        modifier = Modifier.size(36.dp)
                    )
                }

                IconButton(onClick = { navController.navigate(Destinations.USER_PROFILE) }) {
                    Icon(
                        Icons.Rounded.AccountCircle,
                        contentDescription = "Perfil",
                        modifier = Modifier
                            .size(36.dp)
                            .padding(end = 4.dp)
                    )
                }
            }

            if (currentRoute == PET_LIST) {
                Button(
                    onClick = {
                        navController.navigate(Destinations.PET_REGISTER)
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 8.dp)
                        .width(120.dp)
                        .height(36.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = stringResource(R.string.screen_petlist_navbar_button_text),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }



            if (isPetRoute && section == "profile") {
                IconButton(onClick = {
                    scope.launch {
                        if (snackbarHostState.currentSnackbarData == null) {
                            SnackbarUtils.show(
                                snackbarHostState, message = comingSoonText
                            )
                        }
                    }
                }) {
                    Icon(
                        Icons.Rounded.Notifications,
                        contentDescription = "Notificações",
                        modifier = Modifier.size(36.dp)
                    )
                }

                Box(
                    modifier = Modifier.padding(end = 10.dp) // padding externo
                ) {
                    IconButton(
                        onClick = {
                            petId.let { navController.navigate(Destinations.petOverviewRoute(it)) }
                        },
                        modifier = Modifier.size(32.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Icon(
                            Icons.Rounded.Pets,
                            contentDescription = "Perfil",
                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
            }

        },

        )
}


//@Preview(showBackground = true)
//@Composable
//fun AppNavbarPreview() {
//    CuidaPetTheme {
//
//        val snackbarHostState = remember { SnackbarHostState() }
//        val scope = rememberCoroutineScope()
//        val navController = rememberNavController()
//
//        val demoPet = Pet(
//            id = "123",
//            name = "Lunaaaaaaaaaaaaaaaaaaaa Fernanda",
//            birthdate = LocalDate.of(2021, 4, 12),
//            sex = Sex.FEMALE,
//            species = Species.DOG,
//            breed = "Labrador Retriever",
//            isNeutered = true,
//            microchip = "985112004623589",
//            weightKg = 24.5,
//            color = "Yellow",
//            chronicDiseases = emptyList(),
//            allergies = emptyList(),
//            vaccines = emptyList(),
//            medicalRecords = emptyList(),
//            notes = emptyList(),
//            alerts = emptyList()
//        )
//
//        // mudar no PetItemsProvider de VAL pra VAR
//        PetItemsProvider.pets = listOf(demoPet)
//
//        val testRoute = "pet/${demoPet.id}/profile"
//
//        AppNavbar(
//            currentRoute = testRoute,
//            navController = navController,
//            snackbarHostState = snackbarHostState,
//            scope = scope
//        )
//    }
//}

