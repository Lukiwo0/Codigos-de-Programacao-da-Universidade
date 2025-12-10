package com.example.cuidapet.ui.screens.user.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cuidapet.R
import com.example.cuidapet.ui.components.SnackbarUtils
import com.example.cuidapet.ui.screens.user.models.UserIcon
import com.example.cuidapet.ui.theme.CuidaPetTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ComingSoonItem(
    icon: UserIcon,
    label: String,
    snackbarHostState:
    SnackbarHostState,
    scope: CoroutineScope
) {
    val comingSoonText = stringResource(R.string.snackbar_coming_soon_text)

    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 4.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    scope.launch {
                        if (snackbarHostState.currentSnackbarData == null) {
                            SnackbarUtils.show(
                                snackbarHostState, message = comingSoonText
                            )
                        }
                    }
                }
                .padding(12.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserItemIcon(
                    icon = icon,
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = label,
                        style = MaterialTheme.typography.headlineSmall,
                    )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp),
            ) {
                Text(
                    text = "EM BREVE",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ComingSoonItemPreview() {
    CuidaPetTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        ComingSoonItem(
            icon = UserIcon.Resource(R.drawable.google_logo),
            label = "Conectar com Google",
            snackbarHostState = snackbarHostState,
            scope = scope
        )
    }
}
