package org.example.project

data class ProfileUiState(
    val name: String = "Louis Hutabarat",
    val bio: String = "Mahasiswa Teknik Informatika NIM 123140052. Tertarik pada Mobile Dev & IoT.",
    val email: String = "louis.123140052@student.itera.ac.id", // Tambahan baru
    val phone: String = "+62 813-9780-3190",                  // Tambahan baru
    val location: String = "Bandar Lampung, Indonesia",       // Tambahan baru
    val isDarkMode: Boolean = false,
    val isEditing: Boolean = false
)
