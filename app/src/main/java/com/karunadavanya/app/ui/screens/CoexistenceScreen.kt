package com.karunadavanya.app.ui.screens

import androidx.compose.foundation.background
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
fun CoexistenceScreen(animal: String, onBack: () -> Unit) {

    val data = mapOf(
        "elephant" to Triple("🐘 Elephant Encounter", AmberWarn, listOf(
            "Stay calm and speak softly — sudden movements trigger charge",
            "Never position yourself between a mother and calf",
            "Slowly back away and find solid shelter (building or vehicle)",
            "If mock-charged, stand still — running triggers pursuit",
            "Alert forest department at 1800-425-7852 immediately"
        )),
        "tiger" to Triple("🐅 Tiger Protocol", Color(0xFFE11D48), listOf(
            "Make yourself look large — raise arms and jacket",
            "Never run. Tigers are triggered by movement & pursuit",
            "Maintain eye contact and back away slowly",
            "Travel in groups — tigers avoid larger parties",
            "Do not enter forest after dark without escort"
        )),
        "leopard" to Triple("🐆 Leopard Safety", Secondary, listOf(
            "Leopards are ambush predators — avoid dense shrubs at dusk",
            "Install adequate lighting near forest-edge villages",
            "Secure livestock in sturdy enclosures by nightfall",
            "Never leave children unsupervised near forest edges",
            "If stalked, shout loudly and throw objects"
        ))
    )

    val (title, color, steps) = data[animal] ?: data["elephant"]!!

    Column(Modifier.fillMaxSize().background(BgDark)) {
        Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null, tint = TextGray) }
            Spacer(Modifier.width(8.dp))
            Text(title, color = color, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
        Column(
            Modifier.verticalScroll(rememberScrollState()).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(BgCard).padding(16.dp)) {
                Column {
                    Spacer(Modifier.height(8.dp))
                    steps.forEachIndexed { i, step ->
                        Row(Modifier.padding(bottom = 12.dp)) {
                            Box(
                                Modifier.size(28.dp).clip(RoundedCornerShape(8.dp)).background(color.copy(0.15f)),
                                Alignment.Center
                            ) {
                                Text("${i+1}", color = color, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(Modifier.width(12.dp))
                            Text(step, color = TextGray, fontSize = 14.sp, lineHeight = 21.sp)
                        }
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}