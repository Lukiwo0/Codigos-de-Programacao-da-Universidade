package com.example.cuidapet.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cuidapet.R
import com.example.cuidapet.ui.components.AppBottomBar
import com.example.cuidapet.ui.components.AppNavbar
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.theme.CuidaPetTheme

@Composable
fun CreditsScreen(navController: NavHostController) {
    CreditsScreenContent(
        onPetListClick = { navController.navigate(Destinations.PET_LIST) },
        onUserProfileClick = { navController.navigate(Destinations.USER_PROFILE) },
        onAllNotificationsClick = { navController.navigate(Destinations.ALL_NOTIFICATIONS) },
        onPetCareTipsClick = { navController.navigate(Destinations.PET_CARE_TIPS) },
        onSupportClick = { navController.navigate(Destinations.SUPPORT) },
        onCreditsClick = { navController.navigate(Destinations.CREDITS) },
    )
}


@Composable
fun CreditsScreenContent(
    onPetListClick: () -> Unit = {},
    onUserProfileClick: () -> Unit = {},
    onAllNotificationsClick: () -> Unit = {},
    onPetCareTipsClick: () -> Unit = {},
    onSupportClick: () -> Unit = {},
    onCreditsClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val title = stringResource(R.string.screen_credits_title)
    val text1 = stringResource(R.string.screen_credits_text_1)
    val text2 = stringResource(R.string.screen_credits_text_2)
    val text3 = stringResource(R.string.screen_credits_text_3)
    val linkText = stringResource(R.string.screen_credits_link_text)
    val linkUrl = stringResource(R.string.screen_credits_link)

    val annotatedLinkText = buildAnnotatedString {
        append(text3 + " ")
        withLink(LinkAnnotation.Url(linkUrl)) {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(linkText)
            }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 130.dp, top = 16.dp)
    ) {
        item {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = text1, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = text2, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = annotatedLinkText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun CreditsScreenPreview() {
    CuidaPetTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        Scaffold(
            topBar = { AppNavbar(currentRoute = Destinations.HOME, navController = rememberNavController(), snackbarHostState = snackbarHostState, scope = scope) },
            bottomBar = { AppBottomBar(navController = rememberNavController()) }
        ) { padding ->
            CreditsScreenContent(modifier = Modifier.padding(padding))
        }
    }
}