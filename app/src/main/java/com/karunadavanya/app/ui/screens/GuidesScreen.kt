package com.karunadavanya.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karunadavanya.app.ui.theme.*

@Composable
fun GuidesScreen(onBack: () -> Unit, onCoexistence: (String) -> Unit, onAudio: () -> Unit) {
    Column(Modifier.fillMaxSize().background(BgDark)) {
        Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null, tint = TextGray) }
            Spacer(Modifier.width(8.dp))
            Text("Field Protocols", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Light)
        }
        Column(Modifier.verticalScroll(rememberScrollState()).padding(horizontal = 16.dp).padding(bottom = 32.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            GuideCard("🐘","Elephant Encounter Protocol","What to do when you spot an elephant herd near settlements or roads.",AmberWarn) { onCoexistence("elephant") }
            GuideCard("🐅","Tiger Safety Guidelines","Essential safety measures for forest workers and tourists in tiger zones.",Color(0xFFE11D48)) { onCoexistence("tiger") }
            GuideCard("🐆","Leopard Conflict Protocol","Steps to prevent and respond to leopard-human conflict situations.",Secondary) { onCoexistence("leopard") }
            GuideCard("🔊","Animal Sound Library","Identify wildlife by their calls. Critical for night patrol safety.",Primary, onAudio)
            GuideCard("📞","Emergency Contacts","Forest Department hotlines and emergency response numbers.",Color(0xFF6366F1)) {}
            GuideCard("🚑","First Aid in Wildlife Zones","Basic first aid procedures for wildlife-related injuries.",Color(0xFFF87171)) {}
        }
    }
}

@Composable
fun GuideCard(icon: String, title: String, desc: String, color: Color, onClick: () -> Unit) {
    Box(Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(BgCard).clickable(onClick = onClick).padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(52.dp).clip(RoundedCornerShape(14.dp)).background(color.copy(0.15f)), Alignment.Center) { Text(icon, fontSize = 24.sp) }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(title, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(4.dp))
                Text(desc, color = TextGray, fontSize = 12.sp, lineHeight = 17.sp)
            }
            Text("→", color = color, fontSize = 18.sp)
        }
    }
}