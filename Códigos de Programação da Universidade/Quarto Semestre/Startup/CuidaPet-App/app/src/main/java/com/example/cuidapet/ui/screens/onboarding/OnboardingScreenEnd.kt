package com.example.cuidapet.ui.screens.onboarding

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import com.example.cuidapet.R
import com.example.cuidapet.data.preferences.UserPreferences
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.theme.CuidaPetTheme
import com.example.cuidapet.ui.theme.Inter
import com.example.cuidapet.ui.theme.Itim
import kotlinx.coroutines.launch

@SuppressLint("ContextCastToActivity")
@Composable
fun OnboardingScreenEnd(
    navController: NavController,
    userPreferences: UserPreferences,
    onboardingViewModel: OnboardingViewModel
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    OnboardingScreenEndContainer(
        onBackClick = { navController.popBackStack() },
        onButtonClick = {
            onboardingViewModel.saveOnboardingData {
                coroutineScope.launch {
                    userPreferences.setOnboardingDone(true)
                }

                val activity = context as? ComponentActivity
                activity?.let {
                    WindowCompat.setDecorFitsSystemWindows(it.window, true)
                    val controllerWindows =
                        WindowCompat.getInsetsController(it.window, it.window.decorView)
                    controllerWindows.show(WindowInsetsCompat.Type.systemBars())
                }

                navController.navigate(Destinations.MAIN) {
                    popUpTo(Destinations.ONBOARDING) {
                        inclusive = true
                    }
                }
            }
        }
    )
}

@Composable
fun OnboardingScreenEndContainer(
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

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp)
        ) {
            I

            OnboardingScreenEndTitle()

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(R.drawable.character_adult_with_dog_love),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(16.dp))

            OnboardingScreenEndTextsAndButton(onButtonClick = onButtonClick)

        }

    }
}


@Composable
fun OnboardingScreenEndTextsAndButton(
    onButtonClick: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.welcome_end_text_1),
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = Itim,
            fontSize = 22.sp,
            lineHeight = 24.sp,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.textwhitevariant),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.welcome_end_text_2),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = Inter,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            lineHeight = 16.sp,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.textblack),
            modifier = Modifier.padding(horizontal = 12.dp)
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
                text = stringResource(R.string.welcome_end_button),
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = Itim,
                fontSize = 24.sp,
                modifier = Modifier.padding(all = 2.dp)
            )
        }
    }
}


@Composable
fun OnboardingScreenEndTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.welcome_end_title),
        fontFamily = Itim,
        textAlign = TextAlign.Center,
        lineHeight = 35.sp,
        modifier = Modifier
            .padding(top = 10.dp),
        style = MaterialTheme.typography.displayLarge.copy(
            fontSize = 60.sp,
            shadow = Shadow(
                color = Color.Black.copy(alpha = 0.5f),
                offset = Offset(x = 4f, y = 4f),
                blurRadius = 8f
            )
        ),
        color = colorResource(R.color.primary)
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenEndPreview() {
    CuidaPetTheme {
        OnboardingScreenEndContainer()
    }
}

//@Preview (showBackground = true)
//@Composable
//fun LightThemePreview() {
//    CuidaPetTheme {
//        OnboardingScreenEndContainer()
//    }
//}
//
//@Preview
//@Composable
//fun DarkThemePreview() {
//    CuidaPetTheme (darkTheme = true){
//        OnboardingScreenEndContainer()
//    }
//}
