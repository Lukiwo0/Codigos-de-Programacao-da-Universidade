package com.example.cuidapet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.theme.CuidaPetTheme


@Composable
fun AppBottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem(Destinations.HOME, Icons.Rounded.Home),
        BottomNavItem(Destinations.PET_LIST, Icons.Rounded.Pets),
        BottomNavItem(Destinations.USER_PROFILE, Icons.Rounded.Person)
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(bottom = 20.dp),
//            .windowInsetsPadding(WindowInsets.navigationBars), Isso pega o tamanho da barra de navageção do android e coloca como padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(60.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(30.dp),
                    clip = false
                ),
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

                items.forEach { item ->
                    IconButton(
                        onClick = { navController.navigate(item.route) },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            imageVector = item.icon,
                            contentDescription = item.route,
                            tint = if (currentRoute == item.route)
                                MaterialTheme.colorScheme.onPrimary
                            else
                                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }

    }
}

data class BottomNavItem(val route: String, val icon: ImageVector)


@Preview(showBackground = true)
@Composable
fun AppBottomBarPreview() {
    CuidaPetTheme {
        AppBottomBar(navController = rememberNavController())
    }
}