package com.example.cuidapet.ui.screens.user.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cuidapet.ui.screens.user.models.UserIcon
import com.example.cuidapet.ui.theme.CuidaPetTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun EditableItem(
    id: String,
    icon: UserIcon,
    label: String,
    value: String,
    onClick: () -> Unit = {}
) {

    // PARA TESTE
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var edit by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(vertical = 2.dp)) {

            Text(
                text = label,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(start = 12.dp, bottom = 4.dp),
            )

            Surface(
                modifier = Modifier
                    .clickable { onClick() },
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(12.dp),
                shadowElevation = 4.dp,
            ) {

                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    UserItemIcon(
                        icon = icon,
                        modifier = Modifier.size(28.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {

                        Text(text = value, style = MaterialTheme.typography.bodyLarge)
                    }

                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = "Editar",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onClick() }
                    )
                }
            }

//            if (edit) {
//                UserFieldPopup(
//                    id = id,
//                    label = label,
//                    initialValue = value,
//                    onDismiss = { edit = false },
//                    onSave = { newValue ->
//
//                        // PARA TESTE
//                        coroutineScope.launch {
//                            snackbarHostState.currentSnackbarData?.dismiss()
//                            snackbarHostState.showSnackbar("Salvou $label -> $newValue")
//                        }
//                        edit = false
//                    }
//                )
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditableItemPreview() {
    CuidaPetTheme {
        EditableItem(
            id = "miguel",
            icon = UserIcon.Vector(Icons.Rounded.Person),
            label = "Nome",
            value = "Miguel",
        )
    }
}
