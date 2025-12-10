package com.example.cuidapet.ui.screens.pets.register.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cuidapet.ui.screens.pets.models.Pet
import com.example.cuidapet.ui.screens.pets.register.PetRegisterFormState
import com.example.cuidapet.ui.screens.pets.register.toPet
import java.time.format.DateTimeFormatter



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetRegisterForm(
    state: PetRegisterFormState,
    onStateChange: (PetRegisterFormState) -> Unit,
    onFieldClick: (String) -> Unit,
    onSubmit: (Pet) -> Unit
) {
    val dateFormatter = remember { DateTimeFormatter.ofPattern("dd/MM/yyyy") }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        OutlinedTextField(
            value = state.name,
            onValueChange = { newName  ->
                val error = PetRegisterValidators.validateName(newName)

                val newErrors = state.errors.toMutableMap()
                if (error != null) {
                    newErrors["name"] = error
                } else {
                    newErrors.remove("name")
                }

                onStateChange(state.copy(name = newName, errors = newErrors))
            },
            isError = state.errors["name"] != null,
            label = { Text("Nome do Pet") },
            modifier = Modifier.fillMaxWidth()
        )
        state.errors["name"]?.let { Text(it, color = Color.Red) }

        OutlinedTextField(
            value = state.birthdate?.format(dateFormatter) ?: "",
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onFieldClick("birthdate") },
            label = { Text("Data de Nascimento") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.EditCalendar,
                    contentDescription = null
                )
            },
            isError = state.errors["birthdate"] != null,
            colors = TextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledIndicatorColor = MaterialTheme.colorScheme.outline,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
            ),
            enabled = false
        )
        state.errors["birthdate"]?.let { Text(it, color = Color.Red) }

        OutlinedTextField(
            value = state.sex?.label ?: "",
            onValueChange = {},
            readOnly = true,
            enabled = false,
            label = { Text("Sexo") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onFieldClick("sex") },
            isError = state.errors["sex"] != null,
            colors = TextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledIndicatorColor = MaterialTheme.colorScheme.outline,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
            )
        )
        state.errors["sex"]?.let { Text(it, color = Color.Red) }

        OutlinedTextField(
            value = state.species?.label ?: "",
            onValueChange = {},
            readOnly = true,
            enabled = false,
            label = { Text("Selecione a espécie") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onFieldClick("species" ) },
            isError = state.errors["species"] != null,
            colors = TextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledIndicatorColor = MaterialTheme.colorScheme.outline,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
            )
        )
        state.errors["species"]?.let { Text(it, color = Color.Red) }

        OutlinedTextField(
            value = state.isNeutered?.label ?: "",
            onValueChange = {},
            readOnly = true,
            enabled = false,
            label = { Text("Selecione o Status de Castração") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onFieldClick("isNeutered") },
            isError = state.errors["isNeutered"] != null,
            colors = TextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledIndicatorColor = MaterialTheme.colorScheme.outline,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
            )
        )
        state.errors["isNeutered"]?.let { Text(it, color = Color.Red) }

        // --- BREED ---
        OutlinedTextField(
            value = state.breed,
            onValueChange = { newBreed ->
                val error = PetRegisterValidators.validateBreed(newBreed)
                val newErrors = state.errors.toMutableMap()
                if (error != null) newErrors["breed"] = error else newErrors.remove("breed")

                onStateChange(state.copy(breed = newBreed, errors = newErrors))
            },
            label = { Text("Raça (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )


        OutlinedTextField(
            value = state.microchip,
            onValueChange = { newMicrochip ->
                val error = PetRegisterValidators.validateMicrochip(newMicrochip)

                val newErrors = state.errors.toMutableMap()
                if (error != null) {
                    newErrors["microchip"] = error
                } else {
                    newErrors.remove("microchip")
                }

                onStateChange(state.copy(microchip = newMicrochip, errors = newErrors))
            },
            isError = state.errors["microchip"] != null,
            label = { Text("Microchip (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )
        state.errors["microchip"]?.let { Text(it, color = Color.Red) }

        OutlinedTextField(
            value = state.weightKg,
            onValueChange = { newWeight ->
                val error = PetRegisterValidators.validateWeight(newWeight)
                val newErrors = state.errors.toMutableMap()
                if (error != null) newErrors["weightKg"] = error else newErrors.remove("weightKg")

                onStateChange(state.copy(weightKg = newWeight, errors = newErrors))
            },
            isError = state.errors["weightKg"] != null,
            label = { Text("Peso (kg) - opcional") },
            modifier = Modifier.fillMaxWidth()
        )
        state.errors["weightKg"]?.let { Text(it, color = Color.Red) }

        OutlinedTextField(
            value = state.color,
            onValueChange = {
                onStateChange(state.copy(color = it))
            },
            label = { Text("Cor (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.chronicDiseases,
            onValueChange = {
                onStateChange(state.copy(chronicDiseases = it))
            },
            label = { Text("Doenças Crônicas (separar com vírgula)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.allergies,
            onValueChange = {
                onStateChange(state.copy(allergies = it))
            },
            label = { Text("Alergias (separar com vírgula)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val allErrors = PetRegisterValidators.validateAll(state)

                onStateChange(state.copy(errors = allErrors))

                if (allErrors.isEmpty()) {
                    val pet = state.toPet()
                    onSubmit(pet)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar Pet")
        }
    }

}




