package com.example.cuidapet.ui.screens.tips

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cuidapet.data.petcare.PetCareData
import com.example.cuidapet.data.petcare.PetTip

@Composable
fun PetTipDetailScreen(navController: NavHostController, petType: String, petCategory: String ) {
    val detail = PetCareData.getTipDetail(petType, petCategory)
    PetTipDetailScreenContent(detail, petType)
}

@Composable
fun PetTipDetailScreenContent(
    detail: PetTip,
    petType: String
) {
    val urlHandler = LocalUriHandler.current

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = detail.title + " para " + petType.lowercase(),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary,
            )

        Spacer(Modifier.height(32.dp))

        Text(
            text = detail.text,
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = detail.sourceName,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                urlHandler.openUri(detail.sourceUrl)
            }
        )

    }
}