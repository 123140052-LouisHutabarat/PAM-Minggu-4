package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import minggu4pam.composeapp.generated.resources.Res
import minggu4pam.composeapp.generated.resources.luis
import org.jetbrains.compose.resources.painterResource

@Composable
fun App() {
    val viewModel = remember { ProfileViewModel() }
    val uiState by viewModel.uiState.collectAsState()
    val bgColor = if (uiState.isDarkMode) Color(0xFF121212) else Color(0xFFF5F5F5)

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = bgColor) {
            MyProfileApp(uiState = uiState, viewModel = viewModel)
        }
    }
}

@Composable
fun MyProfileApp(uiState: ProfileUiState, viewModel: ProfileViewModel) {
    val textColor = if (uiState.isDarkMode) Color.White else Color.Black
    val themeLabel = if (uiState.isDarkMode) "Dark Mode" else "Light Mode"

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(themeLabel, fontWeight = FontWeight.Bold, color = textColor)
            Switch(
                checked = uiState.isDarkMode,
                onCheckedChange = { isDark -> viewModel.toggleDarkMode(isDark) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.isEditing) {
            EditProfileForm(
                currentName = uiState.name,
                currentBio = uiState.bio,
                currentEmail = uiState.email,       
                currentPhone = uiState.phone,      
                currentLocation = uiState.location, 
                isDarkMode = uiState.isDarkMode,
                onSave = { name, bio, email, phone, location ->
                    viewModel.saveProfile(name, bio, email, phone, location)
                },
                onCancel = { viewModel.setEditingMode(false) }
            )
        } else {
            Box(contentAlignment = Alignment.Center) {
                ProfileCard(
                    name = uiState.name,
                    bio = uiState.bio,
                    email = uiState.email,       
                    phone = uiState.phone,       
                    location = uiState.location, 
                    isDarkMode = uiState.isDarkMode,
                    onEditClick = { viewModel.setEditingMode(true) }
                )
            }
        }
    }
}

@Composable
fun EditProfileForm(
    currentName: String,
    currentBio: String,
    currentEmail: String,
    currentPhone: String,
    currentLocation: String,
    isDarkMode: Boolean,
    onSave: (String, String, String, String, String) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    var nameInput by remember { mutableStateOf(currentName) }
    var bioInput by remember { mutableStateOf(currentBio) }
    var emailInput by remember { mutableStateOf(currentEmail) }
    var phoneInput by remember { mutableStateOf(currentPhone) }
    var locationInput by remember { mutableStateOf(currentLocation) }

    val cardColor = if (isDarkMode) Color(0xFF1E1E1E) else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val labelColor = if (isDarkMode) Color.LightGray else Color.DarkGray
    val textStyle = androidx.compose.ui.text.TextStyle(color = textColor)

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())
        ) {
            Text("Edit Profil Lengkap", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = textColor)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nameInput,
                onValueChange = { nameInput = it },
                label = { Text("Nama Lengkap", color = labelColor) },
                textStyle = textStyle,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = bioInput,
                onValueChange = { bioInput = it },
                label = { Text("Deskripsi Bio", color = labelColor) },
                textStyle = textStyle,
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = emailInput,
                onValueChange = { emailInput = it },
                label = { Text("Alamat Email", color = labelColor) },
                textStyle = textStyle,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = phoneInput,
                onValueChange = { phoneInput = it },
                label = { Text("Nomor Telepon", color = labelColor) },
                textStyle = textStyle,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = locationInput,
                onValueChange = { locationInput = it },
                label = { Text("Lokasi Saat Ini", color = labelColor) },
                textStyle = textStyle,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(onClick = onCancel) {
                    Text("Batal", color = Color(0xFF673AB7))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { onSave(nameInput, bioInput, emailInput, phoneInput, locationInput) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7))
                ) {
                    Text("Simpan", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun ProfileCard(
    name: String,
    bio: String,
    email: String,
    phone: String,
    location: String,
    isDarkMode: Boolean,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val cardBg = if (isDarkMode) Color(0xFF1E1E1E) else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val subTextColor = if (isDarkMode) Color(0xFFAAAAAA) else Color(0xFF616161)
    val dividerColor = if (isDarkMode) Color(0xFF333333) else Color(0xFFE0E0E0)

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            ProfileAvatar()

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = textColor)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = bio, fontSize = 14.sp, color = subTextColor, textAlign = TextAlign.Start)
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = dividerColor, thickness = 1.dp)
                Spacer(modifier = Modifier.height(12.dp))

                InfoItem(icon = Icons.Default.Email, text = email, subTextColor)
                InfoItem(icon = Icons.Default.Phone, text = phone, subTextColor)
                InfoItem(icon = Icons.Default.LocationOn, text = location, subTextColor)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onEditClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7))
                ) {
                    Text("Edit Profile", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun ProfileAvatar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.size(100.dp).clip(CircleShape).background(Color(0xFFEDE7F6)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.luis),
            contentDescription = "Foto Profil",
            modifier = Modifier.size(92.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun InfoItem(icon: ImageVector, text: String, textColor: Color, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF673AB7),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, fontSize = 13.sp, color = textColor)
    }
}
