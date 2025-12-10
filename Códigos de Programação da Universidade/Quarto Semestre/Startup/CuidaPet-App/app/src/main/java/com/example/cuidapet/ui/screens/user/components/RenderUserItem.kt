package com.example.cuidapet.ui.screens.user.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.cuidapet.ui.screens.user.models.UserItem
import kotlinx.coroutines.CoroutineScope

@Composable
fun RenderUserItem(
    item: UserItem,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    onClick: () -> Unit = {}
) {

    when (item) {
        is UserItem.EditableItem ->
            EditableItem(
                id = item.id,
                icon = item.icon,
                label = item.label,
                value = item.value,
                onClick = onClick
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