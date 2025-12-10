package com.example.cuidapet.ui.screens.pets.common

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.cuidapet.ui.screens.pets.models.enums.NeuteredStatus
import com.example.cuidapet.ui.screens.pets.models.enums.Sex
import com.example.cuidapet.ui.screens.pets.models.enums.Species
import com.example.cuidapet.ui.theme.TextWhite
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetFieldPopup(
    id: String,
    label: String,
    initialValue: String,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    var temp by remember { mutableStateOf(initialValue) }
    var error by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    val scrollState = rememberScrollState()
    val maxHeight = LocalConfiguration.current.screenHeightDp.dp * 0.7f

    Dialog(onDismissRequest = onDismiss) {

        Surface(
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .heightIn(min = 200.dp, max = maxHeight)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f, fill = false)
                        .verticalScroll(scrollState)
                ) {

                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    when (id) {
                        "sex" -> {
                            Sex.entries.forEach { option ->

                                val isSelected = temp == option.name

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            temp = option.name
                                            error = null
                                        }
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = {
                                            temp = option.name
                                            error = null
                                        },
                                        colors = RadioButtonDefaults.colors(
                                            MaterialTheme.colorScheme.onSurface
                                        )
                                    )
                                    Text(option.label, modifier = Modifier.padding(start = 8.dp))
                                }
                            }
                        }

                        "species" -> {
                            Species.entries.forEach { option ->

                                val isSelected = temp == option.name

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            temp = option.name
                                            error = null
                                        }
                                        .padding(horizontal = 8.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = {
                                            temp = option.name
                                            error = null
                                        },
                                        colors = RadioButtonDefaults.colors(
                                            MaterialTheme.colorScheme.onSurface
                                        )
                                    )
                                    Text(option.label, modifier = Modifier.padding(start = 8.dp))
                                }
                            }
                        }

                        "birthdate" -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        val cal = Calendar.getInstance()
                                        val datePicker = android.app.DatePickerDialog(
                                            context,
                                            { _, y, m, d ->
                                                val date = LocalDate.of(y, m + 1, d)

                                                val formatter =
                                                    DateTimeFormatter.ofPattern("dd/MM/yyyy")
                                                val formatted = date.format(formatter)

                                                temp = formatted
                                                error = null
                                            },
                                            cal.get(Calendar.YEAR),
                                            cal.get(Calendar.MONTH),
                                            cal.get(Calendar.DAY_OF_MONTH)
                                        )
                                        datePicker.datePicker.minDate =
                                            android.icu.util.Calendar.getInstance().apply {
                                                set(1900, 0, 1)
                                            }.timeInMillis
                                        datePicker.show()
                                    }
                            ) {

                                OutlinedTextField(
                                    value = temp,
                                    onValueChange = {},
                                    readOnly = true,
                                    enabled = false,
                                    isError = error != null,
                                    modifier = Modifier.fillMaxWidth(),
                                    label = { Text(label) },
                                    singleLine = true,
                                    colors = TextFieldDefaults.colors(
                                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        disabledIndicatorColor = MaterialTheme.colorScheme.outline,
                                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent,
                                        disabledContainerColor = Color.Transparent,
                                    ),
                                    trailingIcon = {
                                        Icon(
                                            imageVector = Icons.Rounded.EditCalendar,
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
                        }

                        "isNeutered" -> {
                            NeuteredStatus.entries.forEach { option ->

                                val isSelected = temp == option.name

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            temp = option.name
                                            error = null
                                        }
                                        .padding(horizontal = 8.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = {
                                            temp = option.name
                                            error = null
                                        },
                                        colors = RadioButtonDefaults.colors(
                                            MaterialTheme.colorScheme.onSurface
                                        )
                                    )
                                    Text(option.label, modifier = Modifier.padding(start = 8.dp))
                                }
                            }
                        }


                        else -> {
                            OutlinedTextField(
                                value = temp,
                                onValueChange = {
                                    temp = it
                                    error = null
                                },
                                isError = error != null,
                                modifier = Modifier.fillMaxWidth(),
                                label = { Text(label) }
                            )
                        }
                    }

                    error?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(top = 6.dp, start = 2.dp)
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    Button(
                        onClick = onDismiss,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            disabledContainerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.4f),
                            contentColor = TextWhite,
                            disabledContentColor = TextWhite.copy(alpha = 0.6f)
                        ),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.width(80.dp)
                    ) {
                        Text(text = "Cancelar")
                    }

                    Spacer(Modifier.width(8.dp))

                    Button(
                        onClick = {
                            if (temp.isBlank()) {
                                error = "Selecione uma opção para continuar."
                            } else {
                                onSave(temp)
                            }
                        },
                        enabled = error == null && temp.isNotBlank(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.4f
                            ),
                            contentColor = TextWhite,
                            disabledContentColor = TextWhite.copy(alpha = 0.6f)
                        ),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.width(80.dp)
                    ) {
                        Text(text = "Salvar")
                    }
                }

            }
        }
    }
}

