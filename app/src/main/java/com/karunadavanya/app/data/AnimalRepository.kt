package com.karunadavanya.app.data

import com.karunadavanya.app.R

data class AnimalFact(val icon: String, val title: String, val desc: String)
data class AnimalHabitat(val icon: String, val text: String)
data class AnimalDiet(val icon: String, val text: String)

data class Animal(
    val id: String,
    val name: String,
    val scientific: String,
    val status: String,
    val statusColor: String,
    val imageRes: Int,           // ✅ changed from imageUrl: String
    val weight: String,
    val height: String,
    val life: String,
    val statusAbbr: String,
    val description: String,
    val habitat: AnimalHabitat,
    val diet: AnimalDiet,
    val audioPreview: String? = null,
    val facts: List<AnimalFact> = emptyList()
)

object AnimalRepository {
    val all: List<Animal> = listOf(
        Animal(
            id = "bengal-tiger",
            name = "Bengal Tiger",
            scientific = "PANTHERA TIGRIS",
            status = "Endangered",
            statusColor = "red",
            imageRes = R.drawable.img_bengal_tiger,
            weight = "220kg", height = "110cm", life = "16yrs", statusAbbr = "EN",
            description = "The Bengal tiger is a majestic apex predator primarily found in India. Known for its distinct orange and black coat, it is a symbol of strength and conservation efforts worldwide. They are solitary and territorial, relying on stealth to hunt.",
            habitat = AnimalHabitat("🌲", "Sunderbans, Tropical & Dry Forests of Asia."),
            diet = AnimalDiet("🥩", "Wild boar, Deer, and other medium ungulates."),
            audioPreview = "Tiger Roar",
            facts = listOf(
                AnimalFact("🐅", "Unique Identification", "No two tigers have the same stripe patterns, much like human fingerprints."),
                AnimalFact("🌊", "Expert Swimmers", "Unlike most cats, tigers love water and are known to swim for miles to cross rivers."),
                AnimalFact("🔊", "Paralyzing Roar", "A tiger's roar can be heard 2 miles away and contains infrasonic frequencies.")
            )
        ),
        Animal(
            id = "asiatic-lion",
            name = "Asiatic Lion",
            scientific = "PANTHERA LEO",
            status = "Endangered",
            statusColor = "red",
            imageRes = R.drawable.img_asiatic_lion,
            weight = "190kg", height = "107cm", life = "16yrs", statusAbbr = "EN",
            description = "The Asiatic lion is a Panthera leo population surviving today only in India. Since the turn of the 20th century, its range is restricted to the Gir National Park and environs in Gujarat.",
            habitat = AnimalHabitat("🏜️", "Dry deciduous forests and savanna habitats in Gir, Gujarat."),
            diet = AnimalDiet("🥩", "Chital, sambar deer, nilgai, wild boar, and livestock."),
            audioPreview = "Lion Roar",
            facts = listOf(
                AnimalFact("🦁", "Skin Fold", "The most striking morphological character is a longitudinal fold of skin running along their belly."),
                AnimalFact("👨‍👩‍👧", "Smaller Prides", "Unlike African lions, Asiatic lions live in smaller prides."),
                AnimalFact("🌅", "Restricted Range", "Their entire population is localized to a single ecosystem.")
            )
        ),
        Animal(
            id = "indian-cheetah",
            name = "Indian Cheetah",
            scientific = "ACINONYX JUBATUS",
            status = "Extinct",
            statusColor = "gray",
            imageRes = R.drawable.img_indian_cheetah,
            weight = "50kg", height = "80cm", life = "Extinct", statusAbbr = "EX",
            description = "The Asiatic cheetah, historically known as the Indian cheetah, was declared extinct in India in 1952, largely due to hunting and habitat loss.",
            habitat = AnimalHabitat("🌾", "Once thrived in open lands, semi-arid plains and scrub forests."),
            diet = AnimalDiet("🦌", "Blackbuck, chinkara, and small herbivores."),
            audioPreview = "Cheetah Chirp",
            facts = listOf(
                AnimalFact("⚡", "Built for Speed", "The fastest land animal, capable of reaching 112 km/h in short bursts."),
                AnimalFact("👑", "Coursing Hounds", "Historically tamed and used for hunting by Indian royalty."),
                AnimalFact("🔄", "Reintroduction", "India has recently reintroduced African cheetahs to fill the ecological role.")
            )
        ),
        Animal(
            id = "pink-headed-duck",
            name = "Pink-headed Duck",
            scientific = "RHODONESSA CARYOPHYLLACEA",
            status = "Extinct",
            statusColor = "gray",
            imageRes = R.drawable.img_pink_duck,
            weight = "800g", height = "41cm", life = "Extinct", statusAbbr = "EX",
            description = "The pink-headed duck was a large diving duck once found in the Gangetic plains of India. It has not been conclusively seen in the wild since 1949 and is feared extinct.",
            habitat = AnimalHabitat("🌾", "Tall grass thickets and swamps in the Gangetic plains."),
            diet = AnimalDiet("🌱", "Aquatic plants, seeds, and occasionally small molluscs."),
            audioPreview = "Duck Quack",
            facts = listOf(
                AnimalFact("🦩", "Striking Plumage", "Males had a bright pink body and head."),
                AnimalFact("🛑", "Mysterious Extinction", "Intensive searches in Myanmar and India have yielded nothing."),
                AnimalFact("🥚", "Spherical Eggs", "It was notable for laying nearly perfectly spherical eggs.")
            )
        ),
        Animal(
            id = "indian-elephant",
            name = "Indian Elephant",
            scientific = "ELEPHAS MAXIMUS",
            status = "Endangered",
            statusColor = "red",
            imageRes = R.drawable.img_indian_elephant,
            weight = "4000kg", height = "300cm", life = "60yrs", statusAbbr = "EN",
            description = "The Indian elephant is native to mainland Asia. They are highly intelligent, social animals with a deep cultural significance in India.",
            habitat = AnimalHabitat("🌳", "Grasslands, dry and moist deciduous forests, and evergreens."),
            diet = AnimalDiet("🌿", "Megaherbivores consuming up to 150kg of plant matter daily."),
            audioPreview = "Elephant Trumpet",
            facts = listOf(
                AnimalFact("🧠", "Exceptional Memory", "With the largest brain of any land mammal, they possess remarkable memories."),
                AnimalFact("🐘", "Matriarchal Herds", "Herds are led by the oldest and largest female."),
                AnimalFact("🖐️", "Versatile Trunk", "The trunk contains over 40,000 muscles.")
            )
        ),
        Animal(
            id = "indian-hornbill",
            name = "Indian Hornbill",
            scientific = "BUCEROS BICORNIS",
            status = "Vulnerable",
            statusColor = "amber",
            imageRes = R.drawable.img_hornbill,
            weight = "3kg", height = "120cm", life = "40yrs", statusAbbr = "VU",
            description = "The Great Hornbill is a large, colorful bird found in the forests of India and Southeast Asia. Its impressive size and color make it important in tribal cultures.",
            habitat = AnimalHabitat("🌲", "Large tracts of unlogged primary forests with large fruiting trees."),
            diet = AnimalDiet("🍌", "Primarily frugivorous, focusing on figs, but also eats small animals."),
            audioPreview = "Hornbill Call",
            facts = listOf(
                AnimalFact("🕊️", "Unique Nesting", "The female seals herself inside a tree cavity to lay eggs."),
                AnimalFact("🪶", "Casque", "They sport a prominent yellow and black casque on top of their bill."),
                AnimalFact("🚁", "Noisy Flight", "Their wings lack under-coverts, making their flight very loud.")
            )
        )
    )

    fun byId(id: String): Animal? = all.find { it.id == id }
    val endangered get() = all.filter { it.statusColor == "red" }
    val extinct     get() = all.filter { it.statusColor == "gray" }
    val vulnerable  get() = all.filter { it.statusColor == "amber" }
}