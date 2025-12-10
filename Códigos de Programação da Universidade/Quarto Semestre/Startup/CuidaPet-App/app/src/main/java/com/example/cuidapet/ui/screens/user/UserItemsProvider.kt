package com.example.cuidapet.ui.screens.user

import android.R.id.edit
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Login
import androidx.compose.material.icons.rounded.Cake
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.material.icons.rounded.Sms
import androidx.compose.material.icons.rounded.Transgender
import com.example.cuidapet.R
import com.example.cuidapet.ui.navigation.Destinations
import com.example.cuidapet.ui.screens.user.models.UserIcon
import com.example.cuidapet.ui.screens.user.models.UserItem
import java.time.format.DateTimeFormatter


object UserItemsProvider {
    val authItems = listOf(
        UserItem.ComingSoonItem(
            id = "google",
            label = "Conectar com Google",
            icon = UserIcon.Resource(R.drawable.google_logo),
        ),

        UserItem.ComingSoonItem(
            id = "login",
            label = "Fazer login",
            icon = UserIcon.Vector(Icons.AutoMirrored.Rounded.Login),
        ),

        UserItem.ComingSoonItem(
            id = "signup",
            label = "Criar conta",
            icon = UserIcon.Vector(Icons.AutoMirrored.Rounded.Login),
        )
    )


    @RequiresApi(Build.VERSION_CODES.O)
    fun getBaseUserInfoItems(state: UserProfileState): List<UserItem> {
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        return listOf(
            UserItem.EditableItem(
                id = "name",
                label = "Nome:",
                icon = UserIcon.Vector(Icons.Rounded.Person),
                value = state.name
            ),
            UserItem.EditableItem(
                id = "birthday",
                label = "Data de nascimento:",
                icon = UserIcon.Vector(Icons.Rounded.CalendarMonth),
                value = state.birthdate?.format(dateFormatter) ?: "Selecione"
            ),
            UserItem.EditableItem(
                id = "gender",
                label = "Gênero:",
                icon = UserIcon.Vector(Icons.Rounded.Transgender),
                value = state.gender?.label ?: "Selecione"
            ),
        )
    }

    // Mais campos para quando o usuario estiver logado
    val loggedExtraInfoItems  = listOf(
        UserItem.EditableItem(
            id = "email",
            label = "E-mail",
            icon = UserIcon.Vector(Icons.Rounded.Person),
            value = "emailExample@teste.123"
        ),
    )


    val infoItems = listOf(
        UserItem.PopupItem(
            id = "privacy_policy",
            labelRes = R.string.politica_privacidade_title,
            icon = UserIcon.Vector(Icons.Rounded.Description),
            value = R.string.politica_privacidade_title,
            content = R.string.politica_privacidade_text
        ),

        UserItem.PopupItem(
            id = "terms_use",
            labelRes = R.string.diretrizes_uso_title,
            icon = UserIcon.Vector(Icons.Rounded.Description),
            value = R.string.diretrizes_uso_title,
            content = R.string.diretrizes_uso_text
        ),

        UserItem.NavigationItem(
            id = "support",
            label = "Ajuda e Suporte",
            icon = UserIcon.Vector(Icons.Rounded.Sms),
            destination = Destinations.SUPPORT
        ),

        UserItem.NavigationItem(
            id = "credits",
            label = "Créditos",
            icon = UserIcon.Vector(Icons.Rounded.Pets),
            destination = Destinations.CREDITS
        ),
    )


//    val dangerItems = listOf(
//
//    )

}