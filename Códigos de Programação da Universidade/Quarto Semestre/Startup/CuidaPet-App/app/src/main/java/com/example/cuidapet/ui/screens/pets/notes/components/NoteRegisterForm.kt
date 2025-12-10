package com.example.cuidapet.ui.screens.pets.notes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun NoteRegisterForm(
    title: String,
    content: String,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    titleError: String?,
    contentError: String?
) {

    Column {

        OutlinedTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            modifier = Modifier.fillMaxWidth(),
            isError = titleError != null,
            label = { Text("Title") }
        )

        titleError?.let {
            Text(
                text = it,
                color = androidx.compose.material3.MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            value = content,
            onValueChange = { onContentChange(it) },
            modifier = Modifier.fillMaxWidth(),
            isError = contentError != null,
            label = { Text("Content") },
            minLines = 4
        )

        contentError?.let {
            Text(
                text = it,
                color = androidx.compose.material3.MaterialTheme.colorScheme.error
            )
        }
    }
}
