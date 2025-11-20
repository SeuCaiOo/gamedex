package br.com.seucaio.gamedex.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDexTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackButtonClick: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = { Text(title) },
        navigationIcon = {
            onBackButtonClick?.let { onBackButtonClick ->
                IconButton(onClick = { onBackButtonClick.invoke() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun GameDexTopAppBarPreview() {
    GameDexTheme {
        GameDexTopAppBar(title = stringResource(R.string.app_name), onBackButtonClick = {})
    }
}
