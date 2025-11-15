package br.com.seucaio.gamedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun GameDexNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = GameDexRoute.PlatformList,
        modifier = modifier
    ) {
        composable<GameDexRoute.Home> {}

        composable<GameDexRoute.PlatformList> {}

        composable<GameDexRoute.PlatformDetails> {}
    }
}