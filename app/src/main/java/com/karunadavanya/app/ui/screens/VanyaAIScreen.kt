package com.karunadavanya.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karunadavanya.app.data.ChatMessage
import com.karunadavanya.app.data.GeminiClient
import com.karunadavanya.app.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun VanyaAIScreen(onBack: () -> Unit) {
    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                "Namaskara! 🙏 I am Vanya, your Karnataka Wildlife Intelligence Assistant. Ask me anything about endangered species, forest reserves, conservation, or wildlife safety!",
                isUser = false
            )
        )
    }
    var input by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val systemPrompt = "You are Vanya, an expert AI assistant for Karnataka Wildlife. You know about Bengal Tiger, Asiatic Lion, Indian Cheetah (extinct), Pink-headed Duck (extinct), Indian Elephant, Indian Hornbill, Bandipur, Nagarhole, Bhadra, Dandeli reserves. Answer concisely with emojis."

    Column(
        Modifier
            .fillMaxSize()
            .background(BgDark)
            .imePadding()
    ) {
        // Header
        Row(
            Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, null, tint = TextGray)
            }
            Spacer(Modifier.width(8.dp))
            Box(
                Modifier.size(36.dp).clip(CircleShape).background(Primary.copy(0.2f)),
                Alignment.Center
            ) { Text("🍃", fontSize = 18.sp) }
            Spacer(Modifier.width(10.dp))
            Column {
                Text("Vanya AI", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Text("Karnataka Wildlife Assistant", color = Primary, fontSize = 11.sp)
            }
        }

        // Messages
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(messages) { msg ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (msg.isUser) Arrangement.End else Arrangement.Start
                ) {
                    Box(
                        Modifier
                            .widthIn(max = 280.dp)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 16.dp, topEnd = 16.dp,
                                    bottomStart = if (msg.isUser) 16.dp else 4.dp,
                                    bottomEnd = if (msg.isUser) 4.dp else 16.dp
                                )
                            )
                            .background(if (msg.isUser) Primary.copy(0.15f) else BgCard)
                            .padding(14.dp)
                    ) {
                        Text(
                            msg.text,
                            color = if (msg.isUser) Primary else Color.White,
                            fontSize = 13.sp,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
            if (loading) item {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    Box(
                        Modifier.clip(RoundedCornerShape(16.dp)).background(BgCard).padding(14.dp)
                    ) {
                        Text("Vanya is thinking... 🌿", color = TextGray, fontSize = 13.sp)
                    }
                }
            }
        }

        LaunchedEffect(messages.size) {
            if (messages.isNotEmpty()) listState.animateScrollToItem(messages.size - 1)
        }

        // Quick chips
        if (messages.size == 1) {
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AssistChip(
                    onClick = { input = "Bengal Tiger" },
                    label = { Text("Bengal Tiger 🐅", fontSize = 11.sp) },
                    colors = AssistChipDefaults.assistChipColors(containerColor = BgCard, labelColor = TextGray),
                    border = null
                )
                AssistChip(
                    onClick = { input = "Elephant safety" },
                    label = { Text("Elephant 🐘", fontSize = 11.sp) },
                    colors = AssistChipDefaults.assistChipColors(containerColor = BgCard, labelColor = TextGray),
                    border = null
                )
                AssistChip(
                    onClick = { input = "Nagarhole reserve" },
                    label = { Text("Nagarhole 🌲", fontSize = 11.sp) },
                    colors = AssistChipDefaults.assistChipColors(containerColor = BgCard, labelColor = TextGray),
                    border = null
                )
            }
        }

        // Input bar
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(24.dp))
                    .background(BgCard)
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                BasicTextField(
                    value = input,
                    onValueChange = { input = it },
                    singleLine = false,
                    maxLines = 4,
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = Color.White,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = { inner ->
                        if (input.isEmpty()) {
                            Text("Ask about wildlife...", color = TextGray, fontSize = 14.sp)
                        }
                        inner()
                    }
                )
            }
            FloatingActionButton(
                onClick = {
                    if (input.isBlank() || loading) return@FloatingActionButton
                    val userMsg = input.trim()
                    input = ""
                    messages.add(ChatMessage(userMsg, isUser = true))
                    loading = true
                    scope.launch {
                        val reply = GeminiClient.chat("$systemPrompt\n\nUser: $userMsg")
                        messages.add(ChatMessage(reply, isUser = false))
                        loading = false
                    }
                },
                containerColor = Primary,
                contentColor = Color.Black,
                modifier = Modifier.size(52.dp),
                shape = CircleShape
            ) {
                Icon(Icons.Default.Send, null, modifier = Modifier.size(22.dp))
            }
        }
    }
}