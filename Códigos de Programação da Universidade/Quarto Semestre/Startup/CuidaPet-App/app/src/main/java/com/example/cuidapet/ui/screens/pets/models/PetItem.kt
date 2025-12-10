package com.example.cuidapet.ui.screens.pets.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

sealed class PetIcon {
    data class Vector(val icon: ImageVector) : PetIcon()
    data class Resource(@DrawableRes val resId: Int) : PetIcon()
}

sealed class PetItem {
    abstract val id: String
    abstract val icon: PetIcon
    open val label: String? = null
    @StringRes
    open val labelRes: Int? = null

    data class EditableItem(
        override val id: String,
        override val label: String,
        override val icon: PetIcon,
        val value: String
    ) : PetItem()

    data class PopupItem(
        override val id: String,
        @StringRes override val labelRes: Int,
        override val icon: PetIcon,
        @StringRes val value: Int,
        @StringRes val content: Int
    ) : PetItem()

    data class NavigationItem(
        override val id: String,
        override val label: String,
        override val icon: PetIcon,
        val destination: String
    ) : PetItem()

    data class ComingSoonItem(
        override val id: String,
        override val label: String,
        override val icon: PetIcon
    ) : PetItem()
}
