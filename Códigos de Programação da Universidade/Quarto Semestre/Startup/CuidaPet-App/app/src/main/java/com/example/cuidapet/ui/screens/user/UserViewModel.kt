package com.example.cuidapet.ui.screens.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuidapet.data.repository.UserRepository
import kotlinx.coroutines.launch


class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var uiState by mutableStateOf(UserProfileState())
        private set

    private var currentUserId: String? = null

    init {
        loadCurrentUser()
    }

    private fun loadCurrentUser() {
        viewModelScope.launch {
            userRepository.getUser().collect { user ->
                if (user != null) {
                    currentUserId = user.id
                    uiState = user.toState()
                }
            }
        }
    }

    fun updateState(newState: UserProfileState) {
        uiState = newState
    }

    fun saveChanges() {
        viewModelScope.launch {
            val userToSave = uiState.toUser(existingId = currentUserId)
            userRepository.saveUser(userToSave)
        }
    }
}