package com.example.cuidapet.ui.screens.user.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

sealed class UserIcon {
    data class Vector(val icon: ImageVector) : UserIcon()
    data class Resource(@DrawableRes val resId: Int) : UserIcon()
}

sealed class UserItem {
    abstract val id: String
    abstract val icon: UserIcon
    open val label: String? = null
    @StringRes open val labelRes: Int? = null


    data class EditableItem(
        override val id: String,
        override val label: String,
        override val icon: UserIcon,
        val value: String
    ) : UserItem()

    data class PopupItem(
        override val id: String,
        @StringRes override val labelRes: Int,
        override val icon: UserIcon,
        @StringRes val value: Int,
        @StringRes val content: Int
    ) : UserItem()

    data class NavigationItem(
        override val id: String,
        override val label: String,
        override val icon: UserIcon,
        val destination: String
    ) : UserItem()

    data class ComingSoonItem(
        override val id: String,
        override val label: String,
        override val icon: UserIcon,
    ) : UserItem()

}