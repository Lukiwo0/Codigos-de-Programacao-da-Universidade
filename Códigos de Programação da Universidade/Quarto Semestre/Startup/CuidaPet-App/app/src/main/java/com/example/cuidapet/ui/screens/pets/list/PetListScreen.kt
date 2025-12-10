package com.example.cuidapet.ui.screens.pets.list

import android.R.attr.bottom
import android.R.attr.top
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cuidapet.R
import com.example.cuidapet.ui.AppViewModelProvider
import com.example.cuidapet.ui.components.AppBottomBar
import com.example.cuidapet.ui.components.AppNavbar
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.screens.pets.common.PetItemsProvider
import com.example.cuidapet.ui.screens.pets.list.components.PetListCardTemplate
import com.example.cuidapet.ui.screens.pets.models.Pet
import com.example.cuidapet.ui.theme.CuidaPetTheme

@Composable
fun PetListScreen(
    navController: NavHostController,
    viewModel: PetListViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val pets by viewModel.pets.collectAsState()

    PetListScreenContent(
        pets = pets,
        onPetClick = { petId ->
            navController.navigate(Destinations.petProfileRoute(petId))
        },
        onPetRegisterClick = { navController.navigate(Destinations.PET_REGISTER) }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetListScreenContent(
    pets: List<Pet>,
    onPetClick: (String) -> Unit,
    onPetRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var parentHeightPx by remember { mutableStateOf(0) }
    val density = LocalDensity.current
    val topPaddingDp = with(density) { (parentHeightPx * 0.10f).toDp()}

//    val pets = PetItemsProvider.pets

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 130.dp, top = 16.dp)
    ) {

        if (pets.isEmpty()) {

            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .onGloballyPositioned{ layoutCoordinates ->
                            parentHeightPx = layoutCoordinates.size.height
                        }
                        .padding(top = topPaddingDp),
//                        .padding(top = 64.dp),
//                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Button(
                        onClick = { onPetRegisterClick() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ),
                        shape = RoundedCornerShape(12.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.screen_petlist_button_text),
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.onSecondary,
                            textAlign = TextAlign.Center
                        )
                    }


                    Column(
                        modifier = Modifier
                        .padding(top = 20.dp, bottom = 12.dp)
                        .padding(horizontal = 8.dp)
                    ) {

                        Text(
                            text = stringResource(R.string.screen_petlist_title),
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))


                        Text(
                            text = stringResource(R.string.screen_petlist_text),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }


                    Image(
                        painter = painterResource(R.drawable.animals_group_four),
                        contentDescription = null
                    )

                }
            }

        } else {
            items(pets) { pet ->
                PetListCardTemplate(
                    pet = pet,
                    onClick = { onPetClick(pet.id) }
                )
            }
        }

    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun PetListScreenPreview() {
//    CuidaPetTheme {
//
//
//        val snackbarHostState = remember { SnackbarHostState() }
//        val scope = rememberCoroutineScope()
//        val navController = rememberNavController()
//
//        Scaffold(
//            topBar = {
//                AppNavbar(
//                    currentRoute = Destinations.PET_LIST,
//                    navController = navController,
//                    snackbarHostState = snackbarHostState,
//                    scope = scope
//                )
//            },
//            bottomBar = { AppBottomBar(navController = navController) }
//        ) { padding ->
//            PetListScreenContent(
//                modifier = Modifier.padding(padding),
//                onPetClick = {},
//                onPetRegisterClick = {}
//            )
//        }
//    }
//}
