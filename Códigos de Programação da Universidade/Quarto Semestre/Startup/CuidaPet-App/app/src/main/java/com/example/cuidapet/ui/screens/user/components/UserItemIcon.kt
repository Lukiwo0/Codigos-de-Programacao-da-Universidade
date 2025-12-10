package com.example.cuidapet.ui.screens.user.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cuidapet.ui.screens.user.models.UserIcon

@Composable
fun UserItemIcon(icon: UserIcon, modifier: Modifier = Modifier) {
    when (icon) {
        is UserIcon.Vector ->
            Icon(
                imageVector = icon.icon,
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                tint = Color.Unspecified
            )

        is UserIcon.Resource ->
            Icon(
                painter = painterResource(icon.resId),
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                tint = Color.Unspecified
            )
    }
}