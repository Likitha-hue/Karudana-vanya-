package com.karunadavanya.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

// ── Design Tokens ───────────────────────────────────────────────
val Primary   = Color(0xFF00FF87)
val Secondary = Color(0xFF22D3EE)
val Tertiary  = Color(0xFF7C3AED)
val BgDark    = Color(0xFF080B14)
val BgCard    = Color(0xFF151A26)
val BgAlert   = Color(0xFF1C1615)
val RedAlert  = Color(0xFFE11D48)
val AmberWarn = Color(0xFFF59E0B)
val TextGray  = Color(0xFF9CA3AF)

private val DarkColorScheme = darkColorScheme(
    primary   = Primary,
    secondary = Secondary,
    tertiary  = Tertiary,
    background = BgDark,
    surface    = BgCard,
    onBackground = Color.White,
    onSurface    = Color.White
)

@Composable
fun KarunadaVanyaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
