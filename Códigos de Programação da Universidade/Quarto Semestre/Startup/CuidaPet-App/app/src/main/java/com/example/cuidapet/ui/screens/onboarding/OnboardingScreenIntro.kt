package com.example.cuidapet.ui.screens.onboarding

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cuidapet.R
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.theme.CuidaPetTheme
import com.example.cuidapet.ui.theme.Itim

@Composable
fun OnboardingScreenIntro(
    navController: NavController,
    onboardingViewModel: OnboardingViewModel
) {
    OnboardingScreenIntroContainer(
        onButtonClick = {
            navController.navigate(Destinations.ONBOARDING_MISSION)
        },
    )
}

@Composable
fun OnboardingScreenIntroContainer(
    onButtonClick: () -> Unit = {},
    primaryColor: Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        OnboardingScreenIntroCurvedBackground(
            primaryColor = primaryColor,
            onButtonClick = onButtonClick
        )
        OnboardingScreenIntroTitle(modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun  OnboardingScreenIntroCurvedBackground(
    onButtonClick: () -> Unit = {},
    primaryColor: Color
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    scaleX = 1.5f // 50% mais largo
                }
                .offset(y = 5.dp)
        ) {
            drawOval(
                color = primaryColor,
                topLeft = Offset(0f, 0f),
                size = Size(size.width, size.height * 0.50f)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.53f)
                .background(color = primaryColor)
                .align(Alignment.BottomEnd)
        ) {
            OnboardingScreenIntroTextsAndButton(onButtonClick = onButtonClick)
        }

        val imageSize = 400.dp

        Image(
            painter = painterResource(R.drawable.animals_group_generic),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = -(imageSize - 13.dp))
        )

    }
}


@Composable
fun OnboardingScreenIntroTextsAndButton(
    onButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 70.dp)
    ) {
        Text(
            text = stringResource(R.string.welcome_intro_text_1),
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = Itim,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                .padding(bottom = 4.dp, top = 4.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.welcome_intro_text_2),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = Itim,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(start = 22.dp, end = 22.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.welcome_intro_text_3),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = Itim,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(start = 70.dp, end = 70.dp)
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
                text = stringResource(R.string.welcome_intro_button),
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = Itim,
                fontSize = 25.sp,
                modifier = Modifier.padding(all = 2.dp)
            )
        }
    }
}

@Composable
fun OnboardingScreenIntroTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.brand_name),
        fontFamily = Itim,
        modifier = modifier
            .padding(top = 40.dp),
        style = MaterialTheme.typography.displayLarge.copy(
            fontSize = 80.sp,
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
fun OnboardingScreenIntroPreview() {
    CuidaPetTheme {
        OnboardingScreenIntroContainer()
    }
}

//@Preview
//@Composable
//fun DarkThemePreviewOnboardingScreenFirst() {
//    CuidaPetTheme (darkTheme = true){
//        OnboardingScreenIntroContainerOnboardingScreenFirst()
//    }
//}
