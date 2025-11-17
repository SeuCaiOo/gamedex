package br.com.seucaio.gamedex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.model.game.GameItem
import br.com.seucaio.gamedex.ui.theme.GameDexTheme
import coil3.compose.AsyncImage

@Composable
fun GameDexGridItem(
    id: Int,
    title: String,
    backgroundImageUrl: String?,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit = {}
) {
    Card(
        modifier = modifier.size(120.dp),
        onClick = { onItemClick(id) },
    ) {
        val hasNoImage: Boolean = backgroundImageUrl.isNullOrBlank()

        Column(modifier = Modifier.fillMaxSize()) {
            Box{
                if (!hasNoImage) {
                    AsyncImage(
                        model = backgroundImageUrl,
                        error = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.padding(bottom = 24.dp).fillMaxSize()
                    )

                }
                Column(
                    modifier = Modifier.padding(horizontal = 4.dp).fillMaxSize(),
                    verticalArrangement = if (hasNoImage) Arrangement.Center else Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = title,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.surfaceTint,
                        fontSize = if (hasNoImage) 18.sp else 12.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = if (hasNoImage) 3 else 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

        }
    }
}

@PreviewLightDark
@Composable
private fun GameDexGridItemPreview() {
    GameDexTheme {
        Surface {
            val first = GameItem.sampleList.first()
            Column {
                GameDexGridItem(
                    id = first.id,
                    title = first.name,
                    backgroundImageUrl = null
                )
                GameDexGridItem(
                    id = first.id,
                    title = first.name,
                    backgroundImageUrl = first.imageBackground
                )
            }
        }
    }
}