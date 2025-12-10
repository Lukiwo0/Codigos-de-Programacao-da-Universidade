package com.example.cuidapet.ui.screens.pets.register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cuidapet.ui.AppViewModelProvider
import com.example.cuidapet.ui.components.AppBottomBar
import com.example.cuidapet.ui.components.AppNavbar
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.screens.pets.common.PetFieldPopup
import com.example.cuidapet.ui.screens.pets.models.enums.NeuteredStatus
import com.example.cuidapet.ui.screens.pets.models.enums.Sex
import com.example.cuidapet.ui.screens.pets.models.enums.Species
import com.example.cuidapet.ui.screens.pets.register.components.PetRegisterForm
import com.example.cuidapet.ui.screens.pets.register.components.PetRegisterValidators
import com.example.cuidapet.ui.theme.CuidaPetTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetRegisterScreen(
    navController: NavHostController,
    viewModel: PetRegisterViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    PetRegisterScreenContent(
        state = viewModel.uiState,
        onStateChange = { viewModel.updateState(it) },

        onPetProfileClick = { petId ->
            navController.navigate(Destinations.petProfileRoute(petId))
        },
        onSaveClick = {
            viewModel.savePet {
                navController.popBackStack()
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetRegisterScreenContent(
    state: PetRegisterFormState,
    onStateChange: (PetRegisterFormState) -> Unit,
    onSaveClick: () -> Unit,
    onPetProfileClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var popupId by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 130.dp, top = 16.dp)
    ) {
        item {
            PetRegisterForm(
                state = state,
                onStateChange = onStateChange,
                onFieldClick = { popupId = it },
                onSubmit = { _ ->
                    onSaveClick()
                }
            )
        }
    }

    popupId?.let { id ->
        PetFieldPopup(
            id = id,
            label = when(id) {
                "sex" -> "Sexo"
                "species" -> "Espécie"
                "birthdate" -> "Data de Nascimento"
                "isNeutered" -> "Status de Castração"
                else -> id.replaceFirstChar { it.uppercase() }
            },
            initialValue = when(id) {
                "sex" -> state.sex?.name ?: ""
                "species" -> state.species?.name ?: ""
                "birthdate" -> state.birthdate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""
                "isNeutered" -> state.isNeutered?.name ?: ""
                else -> ""
            },
            onDismiss = { popupId = null },
            onSave = { value ->

                if (value.isBlank()) {
                    popupId = null
                    return@PetFieldPopup
                }

                val newStateBase = when(id) {
                    "sex" -> {
                        val selected = Sex.entries.find { it.name == value }
                        if (selected != null) state.copy(sex = selected) else state
                    }
                    "species" -> {
                        val selected = Species.entries.find { it.name == value }
                        if (selected != null) state.copy(species = selected) else state
                    }
                    "isNeutered" -> {
                        val selected = NeuteredStatus.entries.find { it.name == value }
                        if (selected != null) state.copy(isNeutered = selected) else state
                    }
                    "birthdate" -> {
                        try {
                            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                            state.copy(birthdate = LocalDate.parse(value, formatter))
                        } catch (e: Exception) {
                            state
                        }
                    }
                    else -> state
                }

                val error = when(id) {
                    "sex" -> PetRegisterValidators.validateSex(newStateBase.sex)
                    "species" -> PetRegisterValidators.validateSpecies(newStateBase.species)
                    "birthdate" -> PetRegisterValidators.validateBirthdate(newStateBase.birthdate)
                    "isNeutered" -> PetRegisterValidators.validateIsNeutered(newStateBase.isNeutered)
                    else -> null
                }

                val newErrors = newStateBase.errors.toMutableMap()
                if (error != null) newErrors[id] = error else newErrors.remove(id)

                onStateChange(newStateBase.copy(errors = newErrors))
                popupId = null
            }
        )
    }
}

//
//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun PetRegisterScreenPreview() {
//    CuidaPetTheme {
//        val snackbarHostState = remember { SnackbarHostState() }
//        val scope = rememberCoroutineScope()
//
//        Scaffold(
//            topBar = { AppNavbar(currentRoute = Destinations.HOME, navController = rememberNavController(), snackbarHostState = snackbarHostState, scope = scope) },
//            bottomBar = { AppBottomBar(navController = rememberNavController()) }
//        ) { padding ->
//            PetRegisterScreenContent(
//                modifier = Modifier.padding(padding)
//            )
//        }
//    }
//}
