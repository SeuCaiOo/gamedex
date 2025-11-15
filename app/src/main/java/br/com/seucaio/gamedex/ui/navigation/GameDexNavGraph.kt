package br.com.seucaio.gamedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import br.com.seucaio.gamedex.ui.features.HomeScreen
import br.com.seucaio.gamedex.ui.features.platforms.detail.screen.PlatformDetailsScreen
import br.com.seucaio.gamedex.ui.features.platforms.list.screen.PlatformListScreen

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
        composable<GameDexRoute.Home> {
            HomeScreen(onNavigateToPlatforms = { navController.navigate(GameDexRoute.PlatformList) })
        }

        composable<GameDexRoute.PlatformList> {
            PlatformListScreen(
                onNavigateToDetail = { platformId ->
                    navController.navigate(GameDexRoute.PlatformDetails(platformId))
                }
            )
        }

        composable<GameDexRoute.PlatformDetails> {
            val platform = it.toRoute<GameDexRoute.PlatformDetails>()
            val platformId: Int = platform.id
            PlatformDetailsScreen(
                platformId = platformId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}