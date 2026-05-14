package com.karunadavanya.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import com.google.firebase.firestore.FirebaseFirestore
import com.karunadavanya.app.data.WildlifeAlert
import com.karunadavanya.app.ui.theme.*

@Composable
fun AlertsScreen(onBack: () -> Unit) {
    val alerts = remember { mutableStateListOf<WildlifeAlert>() }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("alerts")
            .addSnapshotListener { snapshot, _ ->
                loading = false
                if (snapshot != null) {
                    alerts.clear()
                    snapshot.documents.forEach { doc ->
                        alerts.add(
                            WildlifeAlert(
                                id = doc.id,
                                title = doc.getString("title") ?: "Wildlife Alert",
                                location = doc.getString("location") ?: "Unknown location",
                                description = doc.getString("description") ?: "",
                                time = "Just now",
                                severity = doc.getString("severity") ?: "info",
                                animalType = doc.getString("animalType") ?: "🐾"
                            )
                        )
                    }
                }
            }
    }

    Column(modifier = Modifier.fillMaxSize().background(BgDark)) {
        // App Bar
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextGray)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(modifier = Modifier.weight(1f)) {
                Text("KARUNADA-", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Light)
                Text("VANYA", color = Primary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color(0xFFE11D48)))
                Spacer(modifier = Modifier.width(4.dp))
                Text("LIVE", color = Color(0xFFE11D48), fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 16.dp)
        ) {
            // Map placeholder
            Box(
                modifier = Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(24.dp)).background(BgCard),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🗺️", fontSize = 48.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Live Threat Map", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    Text("Karnataka Wildlife Zones", color = TextGray, fontSize = 12.sp)
                }
                Box(modifier = Modifier.padding(top = 24.dp, start = 60.dp).size(10.dp).clip(CircleShape).background(Color(0xFFE11D48)).align(Alignment.TopStart))
                Box(modifier = Modifier.padding(top = 80.dp, start = 140.dp).size(8.dp).clip(CircleShape).background(AmberWarn).align(Alignment.TopStart))
                Box(modifier = Modifier.padding(top = 120.dp, start = 220.dp).size(8.dp).clip(CircleShape).background(Secondary).align(Alignment.TopStart))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("ACTIVE ALERTS", color = TextGray, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                if (!loading) Text("${alerts.size} active", color = Primary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (loading) {
                Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Primary, modifier = Modifier.size(32.dp))
                }
            } else if (alerts.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(BgCard).padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("✅", fontSize = 36.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("No active alerts", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                        Text("All clear in your area", color = TextGray, fontSize = 12.sp)
                    }
                }
            } else {
                alerts.forEach { alert ->
                    AlertCard(alert = alert)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun AlertCard(alert: WildlifeAlert) {
    val color = when (alert.severity) {
        "critical" -> Color(0xFFE11D48)
        "warning"  -> AmberWarn
        else       -> Secondary
    }
    Box(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(BgCard).padding(16.dp)
    ) {
        Row {
            Box(
                modifier = Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)).background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Text(alert.animalType, fontSize = 22.sp)
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(alert.title, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                    Text(alert.time, color = color, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(alert.location, color = TextGray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Text(alert.description, color = TextGray.copy(alpha = 0.7f), fontSize = 11.sp, lineHeight = 16.sp)
            }
        }
    }
}