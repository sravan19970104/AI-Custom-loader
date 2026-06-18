package com.loadingbar.generator.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("editor") { EditorScreen(navController) }
        composable("preview/{json}") { PreviewScreen(navController, it.arguments?.getString("json") ?: "") }
        composable("saved") { SavedLoadersScreen(navController) }
    }
}