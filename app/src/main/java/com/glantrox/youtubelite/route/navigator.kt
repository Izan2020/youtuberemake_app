package com.glantrox.youtubelite.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.glantrox.youtubelite.`interface`.DetailScreenUI
import com.glantrox.youtubelite.`interface`.HomeScreenUI
import com.glantrox.youtubelite.providers.YoutubeViewModel

sealed class Screens(val route: String) {
    object detailScreen: Screens("detail_screen")
    object homeScreen: Screens("home_screen")
}

class AppNavigator {

    @Composable
    fun navigations(viewModel: YoutubeViewModel) {
        val navController = rememberNavController()
        NavHost(navController , startDestination = Screens.homeScreen.route ) {
            composable(Screens.homeScreen.route) {
            HomeScreenUI(viewModel, navController)
            }
            composable(Screens.detailScreen.route) {
            DetailScreenUI(viewModel, navController)
            }
        }
    }

    fun push(navController: NavHostController, screen: String) {
        navController.navigate(screen)
    }

    fun pop(navController: NavHostController) {
        navController.popBackStack()
    }

}