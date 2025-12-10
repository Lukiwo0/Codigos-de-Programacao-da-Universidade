package com.example.cuidapet.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cuidapet.R
import com.example.cuidapet.ui.components.AppBottomBar
import com.example.cuidapet.ui.components.AppNavbar
import com.example.cuidapet.ui.components.SupportFAQCard
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.theme.CuidaPetTheme

@Composable
fun SupportScreen(navController: NavHostController) {
    SupportScreenContent()
}

@Composable
fun SupportScreenContent(
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
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)

            ) {
                Text(
                    text = stringResource(R.string.screen_support_intro_title),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.screen_support_intro_subtitle),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }

        }

        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.screen_support_important_title),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.screen_support_important_text),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }

        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.screen_support_faq_title),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.screen_support_faq_subtitle),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            SupportFAQCard(
                question = stringResource(R.string.screen_support_question_1),
                answer = stringResource(R.string.screen_support_answer_1),
            )

            SupportFAQCard(
                question = stringResource(R.string.screen_support_question_2),
                answer = stringResource(R.string.screen_support_answer_2),
            )

            SupportFAQCard(
                question = stringResource(R.string.screen_support_question_3),
                answer = stringResource(R.string.screen_support_answer_3),
            )

            SupportFAQCard(
                question = stringResource(R.string.screen_support_question_4),
                answer = stringResource(R.string.screen_support_answer_4),
            )

            SupportFAQCard(
                question = stringResource(R.string.screen_support_question_5),
                answer = stringResource(R.string.screen_support_answer_5),
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun SupportScreenPreview() {
    CuidaPetTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        Scaffold(
            topBar = { AppNavbar(currentRoute = Destinations.HOME, navController = rememberNavController(), snackbarHostState = snackbarHostState, scope = scope) },
            bottomBar = { AppBottomBar(navController = rememberNavController()) }
        ) { padding ->
            SupportScreenContent(modifier = Modifier.padding(padding))
        }
    }
}