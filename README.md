KarunadaVanya/
├── app/
│   ├── src/main/java/com/karunadavanya/app/
│   │   ├── MainActivity.kt
│   │   ├── data/
│   │   │   ├── AnimalRepository.kt      ← Wildlife species data
│   │   │   └── GeminiClient.kt          ← Gemini AI setup
│   │   └── ui/
│   │       ├── screens/
│   │       │   ├── SplashScreen.kt
│   │       │   ├── HomeScreen.kt
│   │       │   ├── WikiScreen.kt
│   │       │   ├── AnimalDetailScreen.kt
│   │       │   ├── AlertsScreen.kt      ← Firebase real-time
│   │       │   ├── ReportSightingScreen.kt
│   │       │   ├── GuidesScreen.kt
│   │       │   ├── ForestSoundsScreen.kt ← MediaPlayer
│   │       │   ├── CoexistenceScreen.kt
│   │       │   └── VanyaAIScreen.kt     ← Gemini AI chat
│   │       ├── theme/
│   │       │   └── Theme.kt             ← Dark theme + colours
│   │       └── NavGraph.kt              ← Navigation routes
│   └── src/main/res/
│       ├── drawable/                    ← Animal images
│       └── raw/                         ← Audio files (MP3)
├── google-services.json                 ← Firebase config
└── build.gradle.kts
