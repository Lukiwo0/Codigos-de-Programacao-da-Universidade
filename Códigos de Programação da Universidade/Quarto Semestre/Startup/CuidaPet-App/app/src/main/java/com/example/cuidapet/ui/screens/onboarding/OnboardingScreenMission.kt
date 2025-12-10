package com.example.cuidapet.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cuidapet.R
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.theme.CuidaPetTheme
import com.example.cuidapet.ui.theme.Inter
import com.example.cuidapet.ui.theme.Itim

@Composable
fun OnboardingScreenMission(
    navController: NavController,
    onboardingViewModel: OnboardingViewModel
) {
    OnboardingScreenMissionContainer(
        onBackClick = { navController.popBackStack() },
        onButtonClick = {
            navController.navigate(Destinations.ONBOARDING_FEATURES)
        }
    )
}

@Composable
fun OnboardingScreenMissionContainer(
    onBackClick: () -> Unit = {},
    onButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = "Voltar",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(32.dp)
            )
        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            I

            OnboardingScreenMissionTitle()

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(R.drawable.character_young_with_cat),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(16.dp))

            OnboardingScreenMissionTextsAndButton(onButtonClick = onButtonClick)

        }

    }
}


@Composable
fun OnboardingScreenMissionTextsAndButton(
    onButtonClick: () -> Unit = {},
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.welcome_mission_text_1),
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = Itim,
            fontSize = 21.5.sp,
            lineHeight = 24.sp,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.textwhitevariant),
            modifier = Modifier.padding(start = 6.dp, end = 6.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.welcome_mission_text_2),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = Inter,
            fontWeight = FontWeight.Medium,
            fontSize = 11.5.sp,
            lineHeight = 16.sp,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.textblack),
            modifier = Modifier.padding(start = 6.dp, end = 6.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onButtonClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.secondary)
            ),
            shape = RoundedCornerShape(size = 16.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.welcome_mission_button),
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = Itim,
                fontSize = 20.sp,
                modifier = Modifier.padding(all = 2.dp)
            )
        }
    }
}


@Composable
fun OnboardingScreenMissionTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.welcome_mission_title),
        fontFamily = Itim,
        textAlign = TextAlign.Center,
        lineHeight = 35.sp,
        modifier = Modifier
            .padding(top = 10.dp),
        style = MaterialTheme.typography.displayLarge.copy(
            fontSize = 34.sp,
            shadow = Shadow(
                color = Color.Black.copy(alpha = 0.5f),
                offset = Offset(x = 4f, y = 4f),
                blurRadius = 8f
            )
        ),
        color = colorResource(R.color.primary)
    )
}

@Preview (showBackground = true)
@Composable
fun OnboardingScreenMissionPreview() {
    CuidaPetTheme {
        OnboardingScreenMissionContainer()
    }
}

//@Preview (showBackground = true)
//@Composable
//fun LightThemePreview() {
//    CuidaPetTheme {
//        OnboardingScreenMissionContainer()
//    }
//}
//
//@Preview
//@Composable
//fun DarkThemePreview() {
//    CuidaPetTheme (darkTheme = true){
//        OnboardingScreenMissionContainer()
//    }
//}
