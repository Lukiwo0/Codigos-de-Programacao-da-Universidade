package com.example.cuidapet.ui.screens.pets.common

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.cuidapet.ui.screens.user.components.ComingSoonItem
import com.example.cuidapet.ui.screens.user.components.EditableItem
import com.example.cuidapet.ui.screens.user.components.NavigationItem
import com.example.cuidapet.ui.screens.user.components.PopupItem
import com.example.cuidapet.ui.screens.user.models.UserItem
import kotlinx.coroutines.CoroutineScope

@Composable
fun RenderPetItem(
    item: UserItem,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {

    when (item) {
        is UserItem.EditableItem ->
            EditableItem(
                id = item.id,
                icon = item.icon,
                label = item.label,
                value = item.value,
            )

        is UserItem.PopupItem ->
            PopupItem(
                icon = item.icon,
                label = stringResource(item.labelRes),
                content = stringResource(item.content)
            )

        is UserItem.NavigationItem ->
            NavigationItem(
                icon = item.icon,
                label = item.label,
                onClick = { navController.navigate(item.destination) }
            )

        is UserItem.ComingSoonItem ->
            ComingSoonItem(
                icon = item.icon,
                label = item.label,
                snackbarHostState = snackbarHostState,
                scope = scope
            )
    }
}