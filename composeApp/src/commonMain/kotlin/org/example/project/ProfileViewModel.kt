package org.example.project

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun toggleDarkMode(isDark: Boolean) {
        _uiState.update { it.copy(isDarkMode = isDark) }
    }

    fun setEditingMode(isEditing: Boolean) {
        _uiState.update { it.copy(isEditing = isEditing) }
    }

    // Fungsi save sekarang menerima 5 parameter
    fun saveProfile(newName: String, newBio: String, newEmail: String, newPhone: String, newLocation: String) {
        _uiState.update {
            it.copy(
                name = newName,
                bio = newBio,
                email = newEmail,       // Simpan email baru
                phone = newPhone,       // Simpan HP baru
                location = newLocation, // Simpan lokasi baru
                isEditing = false
            )
        }
    }
}