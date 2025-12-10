package com.example.cuidapet.ui.screens.user

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cuidapet.ui.AppViewModelProvider
import com.example.cuidapet.ui.components.AppBottomBar
import com.example.cuidapet.ui.components.AppNavbar
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.screens.user.components.RenderUserItem
import com.example.cuidapet.ui.screens.user.components.UserFieldPopup
import com.example.cuidapet.ui.screens.user.models.Gender
import com.example.cuidapet.ui.screens.user.models.UserItem
import com.example.cuidapet.ui.theme.CuidaPetTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun UserProfileScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    UserProfileScreenContent(
        state = viewModel.uiState,
        onStateChange = { viewModel.updateState(it) },
        onSaveChanges = { viewModel.saveChanges() },

        navController = navController,
        snackbarHostState = snackbarHostState,
        scope = scope,
        )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserProfileScreenContent(
    state: UserProfileState,
    onStateChange: (UserProfileState) -> Unit,
    onSaveChanges: () -> Unit,

    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    modifier: Modifier = Modifier
) {

    var popupId by remember { mutableStateOf<String?>(null) }

    val baseUserInfoItems = remember(state) {
        UserItemsProvider.getBaseUserInfoItems(state)
    }

    val authItems = UserItemsProvider.authItems
    val infoItems = UserItemsProvider.infoItems

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 130.dp, top = 16.dp)
    ) {
        items(authItems) { item ->
            RenderUserItem(
                item = item,
                navController = navController,
                snackbarHostState = snackbarHostState,
                scope = scope
            )
        }

        item {
            Spacer(Modifier.height(26.dp))
        }

        items(baseUserInfoItems) { item ->
            RenderUserItem(
                item = item,
                navController = navController,
                snackbarHostState = snackbarHostState,
                scope = scope,
                onClick = {
                    if (item is UserItem.EditableItem) {
                        popupId = item.id
                    }
                }
            )
        }

        item {
            Spacer(Modifier.height(26.dp))
        }

        items(infoItems) { item ->
            RenderUserItem(
                item = item,
                navController = navController,
                snackbarHostState = snackbarHostState,
                scope = scope
            )
        }
    }

    popupId?.let { id ->

        val labelText = when(id) {
            "name" -> "Nome"
            "birthday" -> "Data de Nascimento"
            "gender" -> "Gênero"
            else -> id
        }

        UserFieldPopup(
            id = id,
            label = labelText,
            initialValue = when(id) {
                "name" -> state.name
                "birthday" -> state.birthdate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""
                "gender" -> state.gender?.label ?: ""
                else -> ""
            },
            onDismiss = { popupId = null },
            onSave = { value ->
                if (value.isBlank()) {
                    popupId = null
                    return@UserFieldPopup
                }

                val newState = when(id) {
                    "name" -> state.copy(name = value)
                    "gender" -> {
                        val newGender = Gender.fromLabel(value)
                        if (newGender != null) state.copy(gender = newGender) else state
                    }
                    "birthday" -> {
                        try {
                            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                            state.copy(birthdate = LocalDate.parse(value, formatter))
                        } catch (e: Exception) {
                            state
                        }
                    }
                    else -> state
                }

                onStateChange(newState)

                onSaveChanges()

                scope.launch {
                    snackbarHostState.currentSnackbarData?.dismiss() // Fecha anterior se houver
                    snackbarHostState.showSnackbar("Salvou $labelText -> $value")
                }

                popupId = null
            }
        )
    }

}


//@Preview(showBackground = true)
//@Composable
//fun UserProfileScreenPreview() {
//    CuidaPetTheme {
//        val snackbarHostState = remember { SnackbarHostState() }
//        val scope = rememberCoroutineScope()
//
//        Scaffold(
//            topBar = { AppNavbar(currentRoute = Destinations.HOME, navController = rememberNavController(), snackbarHostState = snackbarHostState, scope = scope) },
//            bottomBar = { AppBottomBar(navController = rememberNavController()) }
//        ) { padding ->
//            UserProfileScreenContent(
//                navController = rememberNavController(),
//                modifier = Modifier.padding(padding),
//                snackbarHostState = snackbarHostState,
//                scope = scope
//            )
//        }
//    }
//}


