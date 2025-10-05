package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.presentation.screens.ChatBotScreen
import org.example.project.presentation.viewModels.GeminiApiViewModel
import org.koin.compose.koinInject

@Composable
fun NavGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.ChatBotScreen){
        composable(Routes.ChatBotScreen) {
            val geminiApiViewModel = koinInject<GeminiApiViewModel>()
            ChatBotScreen(geminiApiViewModel)
        }
    }
}