package com.karunadavanya.app.data

data class WildlifeAlert(
    val id: String,
    val title: String,
    val location: String,
    val description: String,
    val time: String,
    val severity: String,   // "critical" | "warning" | "info"
    val animalType: String
)

object AlertRepository {
    val active: List<WildlifeAlert> = listOf(
        WildlifeAlert(
            id = "alert-1",
            title = "Elephant Herd Spotted",
            location = "Bandipur Hwy, KM 12",
            description = "Herd of 12 spotted moving towards Sector B-4. High caution advised for night travel.",
            time = "10 mins ago",
            severity = "critical",
            animalType = "🐘"
        ),
        WildlifeAlert(
            id = "alert-2",
            title = "Leopard Tracker Lost",
            location = "Sector 4, Outskirts",
            description = "Collar signal lost near Sector 4 outskirts. Forest guards on alert.",
            time = "2 hours ago",
            severity = "warning",
            animalType = "🐆"
        ),
        WildlifeAlert(
            id = "alert-3",
            title = "Tiger Migration Pattern",
            location = "Nagarhole, Zone C",
            description = "Seasonal migration detected. Extra vigilance recommended for vehicle operators.",
            time = "5 hours ago",
            severity = "info",
            animalType = "🐅"
        )
    )
}
