package com.karunadavanya.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.karunadavanya.app.ui.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(BgDark)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { isMenuOpen = true }) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu", tint = TextGray, modifier = Modifier.size(26.dp))
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("Namaskara 🙏", color = Primary, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    Row {
                        Text("Karunada-", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Light)
                        Text("Vanya", color = Primary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Live Alert Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Brush.linearGradient(colors = listOf(Color(0xFF2A0A0A), Color(0xFF1C1615))))
                    .clickable { navController.navigate(Routes.ALERTS) }
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFF87171)))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("LIVE ALERT", color = Color(0xFFF87171), fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                        }
                        Text("Expires in 4h 23m", color = TextGray, fontSize = 10.sp)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Elephant Alert —\nNagarhole", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Light, lineHeight = 32.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Herd spotted moving towards Sector B-4.\nHigh Caution Advised.", color = TextGray, fontSize = 13.sp, lineHeight = 20.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("View Alert Map →", color = Color(0xFFF87171), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Wildlife Wiki Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(BgCard)
                    .clickable { navController.navigate(Routes.WIKI) }
                    .padding(20.dp)
            ) {
                Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                    Text("DATABASE", color = Primary, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Wildlife Wiki", color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.Light)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("6 species documented", color = TextGray, fontSize = 12.sp)
                }
                Text("→", color = Primary, fontSize = 24.sp, modifier = Modifier.align(Alignment.CenterEnd))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action Cards Row
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ActionTile(
                    title = "Send\nAlert",
                    subtitle = "PRIORITY\nREPORTING",
                    accentColor = Color(0xFFE11D48),
                    emoji = "📡",
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate(Routes.REPORT) }
                )
                ActionTile(
                    title = "AI\nVanya",
                    subtitle = "NEURAL\nASSISTANT",
                    accentColor = Primary,
                    emoji = "🍃",
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate(Routes.VANYA_AI) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Guides CTA
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(BgCard)
                    .clickable { navController.navigate(Routes.GUIDES) }
                    .padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("🛡️", fontSize = 32.sp)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Field Protocols", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        Text("Coexistence guides & emergency contacts", color = TextGray, fontSize = 12.sp)
                    }
                    Text("→", color = AmberWarn, fontSize = 20.sp)
                }
            }
        }

        // Bottom Nav
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BottomNavBar(navController = navController)
        }

        // Side Drawer
        if (isMenuOpen) {
            SideDrawer(navController = navController, onClose = { isMenuOpen = false })
        }
    }
}

@Composable
fun ActionTile(title: String, subtitle: String, accentColor: Color, emoji: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier.height(170.dp).clip(RoundedCornerShape(24.dp)).background(BgCard).clickable(onClick = onClick).padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
            Box(
                modifier = Modifier.size(48.dp).clip(RoundedCornerShape(14.dp)).background(accentColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) { Text(emoji, fontSize = 22.sp) }
            Column {
                Text(title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Light, lineHeight = 26.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(subtitle, color = TextGray, fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp, lineHeight = 14.sp)
            }
        }
    }
}

@Composable
fun SideDrawer(navController: NavController, onClose: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.6f)).clickable(onClick = onClose)
    ) {
        Box(
            modifier = Modifier.fillMaxHeight().fillMaxWidth(0.75f).background(BgCard).clickable(onClick = {}).padding(24.dp)
        ) {
            Column(modifier = Modifier.fillMaxHeight()) {
                Spacer(modifier = Modifier.height(32.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.size(36.dp).clip(CircleShape).background(Primary.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) { Text("🍃", fontSize = 18.sp) }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("KARUNADA-VANYA", color = Primary, fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                }
                Spacer(modifier = Modifier.height(40.dp))
                DrawerItem("🏠", "Dashboard")             { navController.navigate(Routes.HOME);     onClose() }
                DrawerItem("📖", "Wildlife Encyclopedia") { navController.navigate(Routes.WIKI);     onClose() }
                DrawerItem("🛡️", "Protocol Guides")       { navController.navigate(Routes.GUIDES);   onClose() }
                DrawerItem("📡", "Live Alert Map")        { navController.navigate(Routes.ALERTS);   onClose() }
                DrawerItem("🤖", "Ask Vanya AI")          { navController.navigate(Routes.VANYA_AI); onClose() }
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(color = Color.White.copy(alpha = 0.05f))
                Spacer(modifier = Modifier.height(24.dp))
                DrawerItem("📞", "Forest Dept Connect", amber = true) { onClose() }
                DrawerItem("🚨", "Emergency Contacts",  amber = true) { onClose() }
                Spacer(modifier = Modifier.weight(1f))
                Text("v1.0 — Karnataka Forest Dept", color = TextGray.copy(alpha = 0.4f), fontSize = 10.sp)
            }
        }
    }
}

@Composable
fun DrawerItem(icon: String, label: String, amber: Boolean = false, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(icon, fontSize = 18.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Text(label, color = if (amber) AmberWarn else Color.White.copy(alpha = 0.85f), fontSize = 15.sp)
    }
}