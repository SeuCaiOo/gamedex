package br.com.seucaio.gamedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.seucaio.gamedex.ui.components.GameDexTopAppBar
import br.com.seucaio.gamedex.ui.navigation.GameDexNavGraph
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameDexApp()
        }
    }
}

@Composable
fun GameDexApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val showBackButton = navController.previousBackStackEntry != null

    val onBackButtonClick: (() -> Unit)? by lazy {
        if (!showBackButton) return@lazy null
        { navController.popBackStack() }
    }

    GameDexTheme {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                GameDexTopAppBar(
                    title = stringResource(R.string.app_name),
                    onBackButtonClick = onBackButtonClick
                )
            },
        ) { innerPadding ->
            GameDexNavGraph(
                navController = navController,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun GameDexAppPreview() {
    GameDexApp()
}