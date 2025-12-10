package com.example.cuidapet.ui.components

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState

object SnackbarUtils {
    suspend fun show(
        snackbarHostState: SnackbarHostState,
        message: String,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        snackbarHostState.showSnackbar(
            message = message,
            duration = duration
        )
    }
}