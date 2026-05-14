package com.karunadavanya.app.ui.screens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object Routes {
    const val SPLASH   = "splash"
    const val HOME     = "home"
    const val WIKI     = "wiki"
    const val ALERTS   = "alerts"
    const val GUIDES   = "guides"
    const val VANYA_AI = "vanya_ai"
    const val REPORT   = "report"
    const val DETAIL   = "detail/{animalId}"
    const val COEXIST  = "coexistence/{animal}"
    const val AUDIO    = "audio"

    fun detail(id: String) = "detail/$id"
    fun coexist(animal: String) = "coexistence/$animal"
}

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        enterTransition = { fadeIn(animationSpec = tween(220)) + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(220)) },
        exitTransition = { fadeOut(animationSpec = tween(220)) + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(220)) },
        popEnterTransition = { fadeIn(animationSpec = tween(220)) + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(220)) },
        popExitTransition = { fadeOut(animationSpec = tween(220)) + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(220)) }
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(onNavigate = {
                navController.navigate(Routes.HOME) { popUpTo(Routes.SPLASH) { inclusive = true } }
            })
        }
        composable(Routes.HOME) { HomeScreen(navController = navController) }
        composable(Routes.WIKI) {
            WikiScreen(onBack = { navController.popBackStack() }, onSelectAnimal = { id -> navController.navigate(Routes.detail(id)) })
        }
        composable(Routes.ALERTS) { AlertsScreen(onBack = { navController.popBackStack() }) }
        composable(Routes.GUIDES) {
            GuidesScreen(
                onBack = { navController.popBackStack() },
                onCoexistence = { animal -> navController.navigate(Routes.coexist(animal)) },
                onAudio = { navController.navigate(Routes.AUDIO) }
            )
        }
        composable(Routes.VANYA_AI) { VanyaAIScreen(onBack = { navController.popBackStack() }) }
        composable(Routes.REPORT) { ReportSightingScreen(onBack = { navController.popBackStack() }) }
        composable(Routes.DETAIL) { backStackEntry ->
            val animalId = backStackEntry.arguments?.getString("animalId") ?: "bengal-tiger"
            AnimalDetailScreen(animalId = animalId, onBack = { navController.popBackStack() }, onAudioPlayer = { navController.navigate(Routes.AUDIO) })
        }
        composable(Routes.COEXIST) { backStackEntry ->
            val animal = backStackEntry.arguments?.getString("animal") ?: "elephant"
            CoexistenceScreen(animal = animal, onBack = { navController.popBackStack() })
        }
        composable(Routes.AUDIO) { AudioPlayerScreen(onBack = { navController.popBackStack() }) }
    }
}