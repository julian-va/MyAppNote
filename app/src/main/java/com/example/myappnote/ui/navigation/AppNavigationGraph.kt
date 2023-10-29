package com.example.myappnote.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myappnote.ui.view.note.NoteScreen

@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.NOTE_SCREEN) {
        composable(Route.NOTE_SCREEN) { NoteScreen(navController) }
    }
}