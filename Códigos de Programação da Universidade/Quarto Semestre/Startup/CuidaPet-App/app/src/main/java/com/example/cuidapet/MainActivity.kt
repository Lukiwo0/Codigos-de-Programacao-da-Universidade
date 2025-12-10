package com.example.cuidapet

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.example.cuidapet.data.preferences.UserPreferences
import com.example.cuidapet.ui.scaffold.AppScaffold
import com.example.cuidapet.ui.theme.CuidaPetTheme
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userPreferences = UserPreferences(this)

        lifecycleScope.launch {
            val onboardingDone = userPreferences.onboardingDone.firstOrNull() ?: false

            if (onboardingDone) {
                // Permite ocupar toda a tela
                WindowCompat.setDecorFitsSystemWindows(window, true)

                // Esconde a barra de navegação e a barra de status
                val controllerWindows = WindowCompat.getInsetsController(window, window.decorView)
                controllerWindows.show(WindowInsetsCompat.Type.systemBars())
            } else {
                WindowCompat.setDecorFitsSystemWindows(window, false)

                val controllerWindows = WindowCompat.getInsetsController(window, window.decorView)
                controllerWindows.hide(WindowInsetsCompat.Type.systemBars())
                controllerWindows.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            }
        }


        setContent {
            CuidaPetTheme {
                Surface(modifier = Modifier.fillMaxSize(),) {
                    AppScaffold()
                }
            }
        }
    }
}