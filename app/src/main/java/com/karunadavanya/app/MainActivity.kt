package com.karunadavanya.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.karunadavanya.app.ui.screens.AppNavGraph
import com.karunadavanya.app.ui.theme.KarunadaVanyaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ FIX: Remove manual FirebaseApp.initializeApp() —
        // Firebase auto-initialises via FirebaseInitProvider in the manifest.
        // Calling it manually on the main thread causes blocking on first launch.

        enableEdgeToEdge()
        setContent {
            KarunadaVanyaTheme {
                AppNavGraph(navController = rememberNavController())
            }
        }
    }
}