package com.example.cuidapet.ui.screens.tips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cuidapet.R
import com.example.cuidapet.data.petcare.PetCareData
import com.example.cuidapet.ui.components.TemplateCard
import com.example.cuidapet.ui.navigation.Destinations

@Composable
fun PetTypeTipsScreen(navController: NavHostController, petType: String) {
    PetTypeTipsScreenContent(
        petType,
        onCategoryClick = { category ->
            navController.navigate("${Destinations.PET_TIP_DETAIL}/$petType/$category")
        }
    )
}

@Composable
fun PetTypeTipsScreenContent(
    petType: String,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 130.dp, top = 16.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.screen_pettypetips_description) + " " + petType.lowercase(),
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(Modifier.height(16.dp))
        }


        items(PetCareData.getTipsForPet(petType)) { tip ->
            TemplateCard(
                title = tip.title,
                onTitleClick = { onCategoryClick(tip.category) }
            )
        }


        item {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Text(
                    text = stringResource(R.string.screen_pettypetips_note),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(16.dp),
                )
            }

        }
    }

}