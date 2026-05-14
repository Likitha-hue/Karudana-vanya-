package com.karunadavanya.app.ui.screens

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karunadavanya.app.ui.theme.*

data class AudioEntry(val emoji: String, val name: String, val animal: String, val rawResName: String)

val audioLibrary = listOf(
    AudioEntry("🐅", "Tiger Roar", "Bengal Tiger", "tiger_roar"),
    AudioEntry("🦁", "Lion Roar", "Asiatic Lion", "lion_roar"),
    AudioEntry("🐘", "Elephant Trumpet", "Indian Elephant", "elephant_trumpet"),
    AudioEntry("🐆", "Leopard Growl", "Indian Leopard", "leopard_growl"),
    AudioEntry("🦅", "Hornbill Call", "Great Hornbill", "hornbill_call"),
    AudioEntry("🦆", "Duck Call", "Pink-headed Duck", "duck_call")
)

@Composable
fun AudioPlayerScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    var currentPlaying by remember { mutableStateOf<String?>(null) }
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    Column(Modifier.fillMaxSize().background(BgDark)) {
        Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { mediaPlayer?.release(); onBack() }) { Icon(Icons.Default.ArrowBack, null, tint = TextGray) }
            Spacer(Modifier.width(8.dp))
            Text("Animal Sound Library", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Light)
        }

        Text("  Tap to identify Karnataka wildlife by sound", color = TextGray, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(16.dp))

        Column(Modifier.verticalScroll(rememberScrollState()).padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            audioLibrary.forEach { entry ->
                val isPlaying = currentPlaying == entry.rawResName
                Box(
                    Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(BgCard)
                        .clickable {
                            mediaPlayer?.release()
                            if (isPlaying) {
                                currentPlaying = null
                            } else {
                                val resId = context.resources.getIdentifier(entry.rawResName, "raw", context.packageName)
                                if (resId != 0) {
                                    mediaPlayer = MediaPlayer.create(context, resId)?.apply {
                                        setOnCompletionListener { currentPlaying = null }
                                        start()
                                    }
                                    currentPlaying = entry.rawResName
                                } else {
                                    // No audio file yet — still show UI
                                    currentPlaying = entry.rawResName
                                }
                            }
                        }.padding(14.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(Modifier.size(52.dp).clip(RoundedCornerShape(14.dp)).background(Primary.copy(if (isPlaying) 0.3f else 0.1f)), Alignment.Center) {
                            Text(entry.emoji, fontSize = 24.sp)
                        }
                        Spacer(Modifier.width(14.dp))
                        Column(Modifier.weight(1f)) {
                            Text(entry.name, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                            Text(entry.animal, color = TextGray, fontSize = 11.sp)
                            if (isPlaying) {
                                Spacer(Modifier.height(6.dp))
                                LinearProgressIndicator(modifier = Modifier.fillMaxWidth().height(2.dp).clip(RoundedCornerShape(1.dp)), color = Primary, trackColor = Color.White.copy(0.1f))
                            }
                        }
                        Spacer(Modifier.width(12.dp))
                        Box(Modifier.size(36.dp).clip(CircleShape).background(if (isPlaying) Primary else Primary.copy(0.15f)), Alignment.Center) {
                            Icon(if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow, null,
                                tint = if (isPlaying) Color.Black else Primary, modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }
            }
            Spacer(Modifier.height(16.dp))
        }
    }

