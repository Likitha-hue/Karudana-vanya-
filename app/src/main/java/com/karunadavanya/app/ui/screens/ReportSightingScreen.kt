package com.karunadavanya.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karunadavanya.app.ui.theme.*

@Composable
fun ReportSightingScreen(onBack: () -> Unit) {
    var selectedAnimal by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var urgency by remember { mutableStateOf("Medium") }
    var submitted by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize().background(BgDark)) {
        Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null, tint = TextGray) }
            Spacer(Modifier.width(8.dp))
            Text("Report Sighting", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Light)
        }

        if (submitted) {
            Box(Modifier.fillMaxSize(), Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("✅", fontSize = 64.sp)
                    Spacer(Modifier.height(16.dp))
                    Text("Report Submitted!", color = Primary, fontSize = 22.sp, fontWeight = FontWeight.Medium)
                    Spacer(Modifier.height(8.dp))
                    Text("Forest Department has been notified.", color = TextGray, fontSize = 14.sp)
                    Spacer(Modifier.height(32.dp))
                    Button(onClick = { submitted = false; selectedAnimal = ""; location = ""; description = "" },
                        colors = ButtonDefaults.buttonColors(containerColor = Primary, contentColor = Color.Black)) {
                        Text("Submit Another Report")
                    }
                }
            }
        } else {
            Column(Modifier.verticalScroll(rememberScrollState()).padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Text("ANIMAL TYPE", color = TextGray, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("🐘 Elephant","🐅 Tiger","🐆 Leopard","🦁 Lion","🦅 Bird").forEach { a ->
                        FilterChip(selected = selectedAnimal == a, onClick = { selectedAnimal = a },
                            label = { Text(a, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Primary.copy(0.2f),
                                selectedLabelColor = Primary,
                                containerColor = BgCard,
                                labelColor = TextGray
                            ))
                    }
                }

                Text("URGENCY LEVEL", color = TextGray, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Low" to Color(0xFF22C55E), "Medium" to AmberWarn, "High" to Color(0xFFE11D48)).forEach { (u, c) ->
                        FilterChip(selected = urgency == u, onClick = { urgency = u },
                            label = { Text(u, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = c.copy(0.2f), selectedLabelColor = c, containerColor = BgCard, labelColor = TextGray))
                    }
                }

                Text("LOCATION", color = TextGray, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                OutlinedTextField(value = location, onValueChange = { location = it }, placeholder = { Text("Enter location / landmark", color = TextGray) },
                    modifier = Modifier.fillMaxWidth(), singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Primary, unfocusedBorderColor = Color.White.copy(0.1f), focusedTextColor = Color.White, unfocusedTextColor = Color.White))

                Text("DESCRIPTION", color = TextGray, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                OutlinedTextField(value = description, onValueChange = { description = it }, placeholder = { Text("Describe what you observed...", color = TextGray) },
                    modifier = Modifier.fillMaxWidth().height(120.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Primary, unfocusedBorderColor = Color.White.copy(0.1f), focusedTextColor = Color.White, unfocusedTextColor = Color.White))

                Spacer(Modifier.height(8.dp))
                Button(onClick = { if (selectedAnimal.isNotEmpty() && location.isNotEmpty()) submitted = true },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48), contentColor = Color.White)) {
                    Text("SUBMIT REPORT", fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
