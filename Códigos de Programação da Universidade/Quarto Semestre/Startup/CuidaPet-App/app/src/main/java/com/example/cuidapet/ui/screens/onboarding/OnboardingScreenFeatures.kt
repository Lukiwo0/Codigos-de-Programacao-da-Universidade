package com.example.cuidapet.ui.screens.onboarding

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
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
fun OnboardingScreenFeatures(
    navController: NavController,
    onboardingViewModel: OnboardingViewModel
) {
    OnboardingScreenFeaturesContainer(
        onBackClick = { navController.popBackStack() },
        onButtonClick = {
            navController.navigate(Destinations.ONBOARDING_USER_DATA)
        }
    )
}

@Composable
fun OnboardingScreenFeaturesContainer(
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    surfaceColor: Color = colorResource(R.color.background),
    onBackClick: () -> Unit = {},
    onButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(WindowInsets.systemBars.asPaddingValues()),
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
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .padding(top = 60.dp, start = 16.dp, end = 16.dp)
        ) {
            OnboardingScreenFeaturesTitle()

            Spacer(modifier = Modifier.height(4.dp))

            Image(
                painter = painterResource(R.drawable.character_young_using_phone),
                contentDescription = null,
            )

        }

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    scaleX = 1.5f // 50% mais largo
                }
                    .offset(y = 95.dp)
        ) {
            drawOval(
                color = surfaceColor,
                topLeft = Offset(0f, 0f),
                size = Size(size.width, size.height * 0.50f)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.44f)
                .align(Alignment.BottomCenter)
                .background(color = surfaceColor)
        ) {
            OnboardingScreenFeaturesTextsAndButton(onButtonClick = onButtonClick)
        }

    }
}


@Composable
fun OnboardingScreenFeaturesTextsAndButton(
    onButtonClick: () -> Unit = {},
) {

    val welcomeThirdListBulletList = stringArrayResource(R.array.welcome_features_list)
    val welcomeThirdListBulletText = welcomeThirdListBulletList.toList().joinToString(separator = "\n") { "• $it"}

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = welcomeThirdListBulletText,
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = Itim,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            textAlign = TextAlign.Left,
            color = colorResource(R.color.textwhitevariant),
            modifier = Modifier.padding(start = 40.dp, end = 40.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.welcome_features_text),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = Inter,
            fontWeight = FontWeight.Medium,
            fontSize = 11.5.sp,
            lineHeight = 16.sp,
            textAlign = TextAlign.Center,
            color = colorResource(R.color.textblack),
            modifier = Modifier.padding(start = 80.dp, end = 80.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onButtonClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.secondary)
            ),
            shape = RoundedCornerShape(size = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.welcome_features_button),
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = Itim,
                fontSize = 20.sp,
                modifier = Modifier.padding(all = 2.dp)
            )
        }
    }
}


@Composable
fun OnboardingScreenFeaturesTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.welcome_features_title),
        fontFamily = Itim,
        textAlign = TextAlign.Center,
        lineHeight = 35.sp,
        style = MaterialTheme.typography.displayLarge.copy(
            fontSize = 34.sp,
            shadow = Shadow(
                color = Color.Black.copy(alpha = 0.5f),
                offset = Offset(x = 4f, y = 4f),
                blurRadius = 8f
            )
        ),
        color = colorResource(R.color.textwhite)
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenFeaturesPreview() {
    CuidaPetTheme {
        OnboardingScreenFeaturesContainer()
    }
}

//@Preview
//@Composable
//fun DarkThemePreview() {
//    CuidaPetTheme (darkTheme = true){
//        OnboardingScreenFeaturesContainer()
//    }
//}
