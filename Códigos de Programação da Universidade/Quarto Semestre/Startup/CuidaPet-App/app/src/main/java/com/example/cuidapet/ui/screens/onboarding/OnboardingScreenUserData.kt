package com.example.cuidapet.ui.screens.onboarding

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cuidapet.R
import com.example.cuidapet.ui.components.TemplateScrollableAlertDialog
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.screens.user.models.Gender
import com.example.cuidapet.ui.theme.CuidaPetTheme
import com.example.cuidapet.ui.theme.Inter
import com.example.cuidapet.ui.theme.Itim
import com.example.cuidapet.ui.theme.Success
import com.example.cuidapet.ui.theme.TextBlack
import com.example.cuidapet.ui.theme.TextWhite
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

@Composable
fun OnboardingScreenUserData(
    navController: NavController,
    onboardingViewModel: OnboardingViewModel
) {
    OnboardingScreenUserDataContainer(
        onBackClick = { navController.popBackStack() },
        onButtonClick = {
            navController.navigate(Destinations.ONBOARDING_PET_DATA)
        },
        viewModel = onboardingViewModel
    )
}

@Composable
fun OnboardingScreenUserDataContainer(
    viewModel: OnboardingViewModel,
    backgroundColor: Color = colorResource(R.color.primary),
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
            .windowInsetsPadding(WindowInsets.systemBars),
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
                .padding(top = 40.dp, start = 16.dp, end = 16.dp)
        ) {
            OnboardingScreenUserDataTitle()

            Spacer(modifier = Modifier.height(4.dp))

            Image(
                painter = painterResource(R.drawable.character_young_with_bird),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )

            OnboardingScreenUserDataForm(
                onButtonClick = onButtonClick,
                viewModel = viewModel
            )
        }


    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OnboardingScreenUserDataForm(
    onButtonClick: () -> Unit = {},
    viewModel: OnboardingViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val dateFormatter = remember { DateTimeFormatter.ofPattern("dd/MM/yyyy") }

    var showPrivacyDialog by remember { mutableStateOf(false) }
    var showGuidelinesDialog by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 80.dp)
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            TextTitleInput(stringResource(R.string.welcome_userdata_form_title_name))
            InputBox {
                BasicTextField(
                    value = viewModel.userState.name,
                    onValueChange = { viewModel.userState = viewModel.userState.copy(name = it) },
                    singleLine = true,
                    textStyle = TextStyle(color = TextBlack, fontSize = 16.sp),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            TextTitleInput(stringResource(R.string.welcome_userdata_form_title_birth))
            InputBox(
                onClick = {
                    val calendar = Calendar.getInstance()
                    val datePicker = android.app.DatePickerDialog(
                        context,
                        { _, year, month, day ->
                            viewModel.updateUserDate(year, month, day)
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    datePicker.datePicker.maxDate = System.currentTimeMillis()
                    datePicker.show()
                }
            ) {
                val dateStr = viewModel.userState.birthdate?.format(dateFormatter)
                Text(
                    text = dateStr ?: "Selecione sua data de nascimento",
                    color = if (dateStr == null) colorResource(R.color.textwhitevariant) else TextBlack,
                    fontSize = 16.sp,
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            OnboardingScreenUserDataGenderPicker(
                selectedGender = viewModel.userState.gender,
                onGenderSelect = { viewModel.userState = viewModel.userState.copy(gender = it) }
            )

            Spacer(modifier = Modifier.height(6.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                PrivacyCheckboxRow(
                    checked = viewModel.userState.acceptedPrivacy,
                    label = stringResource(R.string.welcome_userdata_checkbox_1),
                    onCheckChange = {
                        if (!viewModel.userState.acceptedPrivacy) showPrivacyDialog = true
                        else viewModel.userState =
                            viewModel.userState.copy(acceptedPrivacy = false)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                PrivacyCheckboxRow(
                    checked = viewModel.userState.acceptedGuidelines,
                    label = stringResource(R.string.welcome_userdata_checkbox_2),
                    onCheckChange = {
                        if (!viewModel.userState.acceptedGuidelines) showGuidelinesDialog = true
                        else viewModel.userState =
                            viewModel.userState.copy(acceptedGuidelines = false)
                    }
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val error = viewModel.validateUserStep()
                    if (error != null) {
                        coroutineScope.launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(error)
                        }
                    } else {
                        onButtonClick()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.secondary)),
                shape = RoundedCornerShape(size = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.welcome_userdata_button),
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = Itim,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(2.dp)
                )
            }


            OnboardingScreenUserDataPolicyDialog(
                showDialog = showPrivacyDialog,
                onDismiss = { showPrivacyDialog = false },
                title = stringResource(R.string.politica_privacidade_title),
                text = stringResource(R.string.politica_privacidade_text),
                textbuttonAccept = stringResource(R.string.welcome_userdata_politica_privacidade_button_confirm),
                textbuttonCancel = stringResource(R.string.welcome_userdata_politica_privacidade_button_cancel),
                onAccept = {
                    viewModel.userState = viewModel.userState.copy(acceptedPrivacy = true)
                    showPrivacyDialog = false
                }
            )

            OnboardingScreenUserDataPolicyDialog(
                showDialog = showGuidelinesDialog,
                onDismiss = { showGuidelinesDialog = false },
                title = stringResource(R.string.diretrizes_uso_title),
                text = stringResource(R.string.diretrizes_uso_text),
                textbuttonAccept = stringResource(R.string.welcome_userdata_diretrizes_uso_button_confirm),
                textbuttonCancel = stringResource(R.string.welcome_userdata_diretrizes_uso_button_cancel),
                onAccept = {
                    viewModel.userState = viewModel.userState.copy(acceptedGuidelines = true)
                    showGuidelinesDialog = false
                }
            )
        }
    }
}

@Composable
fun OnboardingScreenUserDataGenderPicker(
    selectedGender: Gender?,
    onGenderSelect: (Gender) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    TextTitleInput(stringResource(R.string.welcome_userdata_form_title_gender))

    InputBox(onClick = { showDialog = true }) {
        Text(
            text = selectedGender?.label ?: "Selecione seu gênero",
            color = if (selectedGender == null) colorResource(R.color.textwhitevariant) else TextBlack,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Selecione seu gênero", fontSize = 20.sp) },
                text = {
                    Column {
                        Gender.entries.forEachIndexed { index, gender ->
                            Text(
                                text = gender.label,
                                fontSize = 12.sp,
                                color = TextWhite,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onGenderSelect(gender)
                                        showDialog = false
                                    }
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RoundedCornerShape(12.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                            if (index < Gender.entries.lastIndex) Spacer(
                                modifier = Modifier.height(
                                    8.dp
                                )
                            )
                        }
                    }
                },
                confirmButton = {},
                containerColor = MaterialTheme.colorScheme.background
            )
        }
    }
}

@Composable
fun OnboardingScreenUserDataPolicyDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    title: String,
    text: String,
    textbuttonAccept: String,
    textbuttonCancel: String,
    onAccept: () -> Unit,
) {
    if (showDialog) {
        TemplateScrollableAlertDialog(
            onDismiss = onDismiss,
            title = title,
            content = text,
            confirmText = textbuttonAccept,
            cancelText = textbuttonCancel,
            onConfirm = onAccept
        )
    }
}

@Composable
fun OnboardingScreenUserDataTitle(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.welcome_userdata_title),
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
            color = colorResource(R.color.textwhite),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.welcome_userdata_title_note),
            fontFamily = Inter,
            fontWeight = FontWeight.Medium,
            fontSize = 9.sp,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp,
            color = colorResource(R.color.textwhite),
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Composable
fun TextTitleInput(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        fontFamily = Itim,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        textAlign = TextAlign.Start,
        color = colorResource(R.color.textwhite),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    )
}

@Composable
fun InputBox(onClick: (() -> Unit)? = null, content: @Composable () -> Unit) {
    Box(
        contentAlignment = if (onClick != null) Alignment.CenterStart else Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(colorResource(R.color.background), RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .let { if (onClick != null) it.clickable { onClick() } else it }
    ) {
        content()
    }
}

@Composable
fun PrivacyCheckboxRow(checked: Boolean, label: String, onCheckChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 4.dp)) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckChange,
            colors = CheckboxDefaults.colors(
                uncheckedColor = MaterialTheme.colorScheme.background,
                checkedColor = Success
            ),
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = label,
            color = colorResource(R.color.textwhite),
            fontSize = 16.sp,
            fontFamily = Itim,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun OnboardingScreenUserDataPreview() {
//    CuidaPetTheme {
//        OnboardingScreenUserDataContainer()
//    }
//}

//@Preview
//@Composable
//fun DarkThemePreview() {
//    CuidaPetTheme (darkTheme = true){
//        OnboardingScreenUserDataContainer()
//    }
//}
