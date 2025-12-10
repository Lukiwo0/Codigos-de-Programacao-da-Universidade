package com.example.cuidapet.ui.screens.tips

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cuidapet.R
import com.example.cuidapet.data.petcare.PetType
import com.example.cuidapet.ui.components.AppBottomBar
import com.example.cuidapet.ui.components.AppNavbar
import com.example.cuidapet.ui.components.SnackbarUtils
import com.example.cuidapet.ui.components.TemplateCard
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.theme.CuidaPetTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PetCareTipsScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    ) {
    PetCareTipsScreenContent(
        onPetClick = { petType ->
            navController.navigate("${Destinations.PET_TYPE_TIPS}/${petType.name}")
        },
        snackbarHostState = snackbarHostState,
        scope = scope,
    )
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun PetCareTipsScreenContent(
    onPetClick: (PetType) -> Unit = {},
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    val comingSoonText = stringResource(R.string.snackbar_coming_soon_text)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 130.dp, top = 16.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.screen_petcaretips_description),
                style = MaterialTheme.typography.bodyMedium,
            )

            Spacer(Modifier.height(16.dp))
        }


        items(PetType.entries) { pet ->
            TemplateCard(
                title = pet.displayName,
                comingSoon = pet.comingSoon,
                onTitleClick = { onPetClick(pet) },
                onComingSoonClick = {
                    scope.launch {
                        if (snackbarHostState.currentSnackbarData == null) {
                            SnackbarUtils.show(
                                snackbarHostState,
                                message = comingSoonText
                            )
                        }
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PetCareTipsScreenPreview() {
    CuidaPetTheme {

        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        Scaffold(
            topBar = { AppNavbar(currentRoute = Destinations.HOME, navController = rememberNavController(), snackbarHostState = snackbarHostState, scope = scope) },
            bottomBar = { AppBottomBar(navController = rememberNavController()) }
        ) { padding ->
            PetCareTipsScreenContent(modifier = Modifier.padding(padding),
                snackbarHostState = snackbarHostState ,
                scope = scope
            )
        }
    }
}