package br.com.seucaio.gamedex.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun GameDexListItem(
    id: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit = {}
) {
    Card(
        modifier = modifier,
        onClick = { onItemClick(id) },
    ) {
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ),
            headlineContent = { Text(title) },
            overlineContent = { Text(description) },
            trailingContent = {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = stringResource(R.string.show_details)
                )
            }
        )
    }
}

@PreviewLightDark
@Composable
private fun GameDexListItemPreview() {
    GameDexTheme {
        Surface {
            val first = GamePlatform.sampleList.first()
            GameDexListItem(
                id = first.id,
                title = first.name,
                description = first.gamesCount.toString()
            )
        }
    }
}
