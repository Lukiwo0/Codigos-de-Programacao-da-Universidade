package com.example.cuidapet.ui.screens.onboarding

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalConfiguration
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.cuidapet.R
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.screens.pets.models.enums.NeuteredStatus
import com.example.cuidapet.ui.screens.pets.models.enums.Sex
import com.example.cuidapet.ui.screens.pets.models.enums.Species
import com.example.cuidapet.ui.theme.CuidaPetTheme
import com.example.cuidapet.ui.theme.Inter
import com.example.cuidapet.ui.theme.Itim
import com.example.cuidapet.ui.theme.TextBlack
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

@Composable
fun OnboardingScreenPetData(
    navController: NavController,
    onboardingViewModel: OnboardingViewModel
) {
    OnboardingScreenPetDataContainer(
        onBackClick = { navController.popBackStack() },
        onButtonClick = {
            navController.navigate(Destinations.ONBOARDING_END)
        },
        viewModel = onboardingViewModel
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OnboardingScreenPetDataContainer(
    viewModel: OnboardingViewModel,
    surfaceColor: Color = colorResource(R.color.background),
    onBackClick: () -> Unit = {},
    onButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()

            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        IconButton(
            onClick = onBackClick, modifier = Modifier
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

        Image(
            painter = painterResource(R.drawable.wave_shape),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .offset(y = -60.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .padding(top = 45.dp)
                .zIndex(1f)
        ) {
            OnboardingScreenPetDataTitle()

            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(R.drawable.animals_group_three),
                contentDescription = null,
                modifier = Modifier.size(210.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.53f)
                .align(Alignment.BottomCenter)
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(bottom = 16.dp)
        ) {
            OnboardingScreenPetDataForm(
                onButtonClick = onButtonClick,
                viewModel = viewModel
            )
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OnboardingScreenPetDataForm(
    onButtonClick: () -> Unit = {},
    viewModel: OnboardingViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val dateFormatter = remember { DateTimeFormatter.ofPattern("dd/MM/yyyy") }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState, modifier = Modifier.padding(bottom = 80.dp)) },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 40.dp)
        ) {
            TextTitleInput(stringResource(R.string.welcome_userdata_form_title_name)) // Reusando string
            InputBox {
                BasicTextField(
                    value = viewModel.petState.name,
                    onValueChange = { viewModel.petState = viewModel.petState.copy(name = it) },
                    singleLine = true,
                    textStyle = TextStyle(color = TextBlack, fontSize = 16.sp),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            TextTitleInput(stringResource(R.string.welcome_userdata_form_title_birth))
            InputBox(onClick = {
                val calendar = Calendar.getInstance()
                val datePicker = android.app.DatePickerDialog(
                    context,
                    { _, year, month, day -> viewModel.updatePetDate(year, month, day) },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePicker.show()
            }) {
                val dateStr = viewModel.petState.birthdate?.format(dateFormatter)
                Text(
                    text = dateStr ?: "Selecione a data de nascimento",
                    color = if (dateStr == null) colorResource(R.color.textwhitevariant) else TextBlack,
                    fontSize = 16.sp,
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            OnboardingPetEnumPicker(
                title = stringResource(R.string.welcome_petdata_form_sex),
                selectedOption = viewModel.petState.sex,
                options = Sex.entries,
                onSelect = { viewModel.petState = viewModel.petState.copy(sex = it) },
                optionLabel = { it.label }
            )


            OnboardingPetEnumPicker(
                title = stringResource(R.string.welcome_petdata_form_species),
                selectedOption = viewModel.petState.species,
                options = Species.entries, // AQUI! Agora vai pegar sua lista gigante do Enum
                onSelect = { viewModel.petState = viewModel.petState.copy(species = it) },
                optionLabel = { it.label }
            )


            OnboardingPetEnumPicker(
                title = stringResource(R.string.welcome_petdata_form_castrated),
                selectedOption = viewModel.petState.isNeutered,
                options = NeuteredStatus.entries,
                onSelect = { viewModel.petState = viewModel.petState.copy(isNeutered = it) },
                optionLabel = { it.label }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val error = viewModel.validatePetStep()
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
        }
    }
}


@Composable
fun <T> OnboardingPetEnumPicker(
    title: String,
    selectedOption: T?,
    options: List<T>,
    onSelect: (T) -> Unit,
    optionLabel: (T) -> String
) {
    var showDialog by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp.dp * 0.7f

    TextTitleInput(title)
    InputBox(onClick = { showDialog = true }) {
        Text(
            text = selectedOption?.let { optionLabel(it) } ?: "Selecione...",
            color = if (selectedOption == null) colorResource(R.color.textwhitevariant) else TextBlack,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(16.dp),
                    tonalElevation = 6.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .heightIn(min = 200.dp, max = maxHeight) // Limita altura
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f, fill = false)
                                .verticalScroll(scrollState)
                        ) {
                            options.forEach { option ->
                                val label = optionLabel(option)
                                val isSelected = option == selectedOption

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            onSelect(option)
                                            showDialog = false
                                        }
                                        .padding(vertical = 12.dp, horizontal = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = {
                                            onSelect(option)
                                            showDialog = false
                                        },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = MaterialTheme.colorScheme.primary,
                                            unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        text = label,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { showDialog = false },
                            modifier = Modifier.align(Alignment.End),
                            colors = ButtonDefaults.textButtonColors()
                        ) {
                            Text("Cancelar")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OnboardingScreenPetDataTitle(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 8.dp, start = 24.dp, end = 24.dp)
    ) {
        Text(
            text = stringResource(R.string.welcome_petdata_title),
            fontFamily = Itim,
            textAlign = TextAlign.Center,
            lineHeight = 40.sp,
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 46.sp, shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.5f),
                    offset = Offset(x = 4f, y = 4f),
                    blurRadius = 8f
                )
            ),
            color = colorResource(R.color.textwhitevariant),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.welcome_petdata_title_note),
            fontFamily = Inter,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp,
            color = colorResource(R.color.textwhitevariant),
            style = MaterialTheme.typography.titleLarge,
        )
    }

}

//@Preview(showBackground = true)
//@Composable
//fun OnboardingScreenPetDataPreview() {
//    CuidaPetTheme {
//        OnboardingScreenPetDataContainer()
//    }
//}

//@Preview
//@Composable
//fun DarkThemePreview() {
//    CuidaPetTheme (darkTheme = true){
//        OnboardingScreenPetDataContainer()
//    }
//}
