package com.example.cuidapet.ui.screens.pets.overview

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cake
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.ContentCut
import androidx.compose.material.icons.rounded.Female
import androidx.compose.material.icons.rounded.Healing
import androidx.compose.material.icons.rounded.Male
import androidx.compose.material.icons.rounded.Memory
import androidx.compose.material.icons.rounded.MonitorWeight
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.icons.rounded.Transgender
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cuidapet.ui.AppViewModelProvider
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.screens.pets.models.Pet
import com.example.cuidapet.ui.screens.pets.models.enums.NeuteredStatus
import com.example.cuidapet.ui.screens.pets.models.enums.Sex
import com.example.cuidapet.ui.screens.pets.models.enums.Species
import com.example.cuidapet.ui.screens.pets.overview.components.PetOverviewCardTemplate
import kotlinx.coroutines.CoroutineScope
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.UUID


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetOverviewScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    petId: String,
    viewModel: PetOverviewViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    LaunchedEffect(petId) {
        viewModel.loadPet(petId)
    }

    val pet = viewModel.pet

    if (pet != null) {
        PetOverviewScreenContent(
            pet = pet,
            navController = navController,
            snackbarHostState = snackbarHostState,
            scope = scope,
            onPetClick = { },
        )
    } else {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetOverviewScreenContent(
    pet: Pet,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    onPetClick: () -> Unit = {},
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
            PetHeaderSection(pet)
        }

        item {
            PetOverviewCardTemplate(title = "Informações Gerais") {
                InfoRow(
                    icon = Icons.Rounded.Pets,
                    label = "Espécie",
                    value = pet.species.label
                )
                InfoRow(
                    icon = Icons.Rounded.ContentCut,
                    label = "Raça",
                    value = pet.breed.ifBlank { "Não definida" }
                )
                InfoRow(
                    icon = if (pet.sex == Sex.MALE) Icons.Rounded.Male else Icons.Rounded.Female,
                    label = "Sexo",
                    value = pet.sex.label
                )
                InfoRow(
                    icon = Icons.Rounded.Palette,
                    label = "Cor",
                    value = pet.color.ifBlank { "-" }
                )
            }
        }

        item {
            PetOverviewCardTemplate(title = "Detalhes Físicos") {
                InfoRow(
                    icon = Icons.Rounded.MonitorWeight,
                    label = "Peso",
                    value = pet.weightKg?.let { "$it kg" } ?: "Não informado"
                )
                InfoRow(
                    icon = Icons.Rounded.Transgender,
                    label = "Castração",
                    value = pet.isNeutered.label
                )
                InfoRow(
                    icon = Icons.Rounded.Memory,
                    label = "Microchip",
                    value = pet.microchip ?: "Não possui"
                )
                InfoRow(
                    icon = Icons.Rounded.CalendarMonth,
                    label = "Nascimento",
                    value = pet.birthdate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                )
            }
        }

        item {
            PetOverviewCardTemplate(title = "Saúde") {

                if (pet.chronicDiseases.isEmpty() && pet.allergies.isEmpty()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Rounded.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Nenhum problema de saúde registrado.")
                    }
                } else {

                    if (pet.chronicDiseases.isNotEmpty()) {
                        Text(
                            "Doenças Crônicas:",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(Modifier.height(4.dp))
                        pet.chronicDiseases.forEach { disease ->
                            HealthItemRow(icon = Icons.Rounded.Healing, text = disease)
                        }
                        Spacer(Modifier.height(12.dp))
                    }

                    if (pet.allergies.isNotEmpty()) {
                        Text(
                            "Alergias:",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(Modifier.height(4.dp))
                        pet.allergies.forEach { allergy ->
                            HealthItemRow(icon = Icons.Rounded.Warning, text = allergy)
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetHeaderSection(pet: Pet) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Pets,
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = pet.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(4.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Rounded.Cake,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = calculateAge(pet.birthdate),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun InfoRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(16.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun HealthItemRow(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(
                MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.1f),
                CircleShape
            )
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun calculateAge(birthDate: LocalDate): String {
    val today = LocalDate.now()
    val period = Period.between(birthDate, today)

    if (period.years > 1) return "${period.years} anos"
    if (period.years == 1) return "1 ano e ${period.months} meses"
    if (period.months > 0) return "${period.months} meses"
    return "${period.days} dias"
}