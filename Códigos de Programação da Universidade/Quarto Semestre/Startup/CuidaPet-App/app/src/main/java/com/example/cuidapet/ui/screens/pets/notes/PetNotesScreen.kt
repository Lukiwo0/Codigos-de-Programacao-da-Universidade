package com.example.cuidapet.ui.screens.pets.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.screens.pets.notes.components.NoteRegisterForm
import kotlinx.coroutines.CoroutineScope


@Composable
fun PetNotesScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    petId: String
) {
    PetNotesScreenContent(
        navController = navController,
        snackbarHostState = snackbarHostState,
        scope = scope,
        onPetVaccinesClick = { navController.navigate(Destinations.PET_LIST) },
    )
}

@Composable
fun PetNotesScreenContent(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    onPetVaccinesClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 130.dp, top = 16.dp)
    ) {

    }
}
