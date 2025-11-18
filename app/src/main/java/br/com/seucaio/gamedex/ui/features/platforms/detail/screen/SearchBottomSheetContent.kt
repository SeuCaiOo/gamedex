package br.com.seucaio.gamedex.ui.features.platforms.detail.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel.PlatformDetailsUiAction
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun SearchBottomSheetContent(
    searchQuery: String,
    onAction: (PlatformDetailsUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.search_game),
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { onAction(PlatformDetailsUiAction.OnSearchQueryChange(it)) },
            label = { Text(stringResource(R.string.name)) },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            singleLine = true
        )

        Button(
            onClick = { onAction(PlatformDetailsUiAction.OnSearchClick) },
            enabled = searchQuery.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.search))
        }
    }
}

@PreviewLightDark
@Composable
private fun SearchBottomSheetContentPreview() {
    GameDexTheme {
        Surface {
            SearchBottomSheetContent(
                searchQuery = "",
                onAction = {}
            )
        }
    }
}
