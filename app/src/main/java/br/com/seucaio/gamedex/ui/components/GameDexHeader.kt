package br.com.seucaio.gamedex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.ui.theme.GameDexTheme
import coil3.compose.AsyncImage

@Composable
fun GameDexHeader(
    title: String,
    modifier: Modifier = Modifier,
    backgroundImageUrl: String? = null
) {
    val hasNoImage: Boolean = backgroundImageUrl.isNullOrBlank()

    if (hasNoImage) {
        return Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = title,
            color = MaterialTheme.colorScheme.surfaceTint,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    }

    Box(
        modifier = modifier.fillMaxWidth().height(250.dp)

    ) {
        AsyncImage(
            model = backgroundImageUrl,
            error = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.surface
                        ),
                        startY = 300f,
                        endY = 700f
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.surfaceTint,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun GameDexHeaderPreview() {
    GameDexTheme {
        Surface {
            Column {
                GameDexHeader(
                    title = "Game",
                    backgroundImageUrl = null
                )
                GameDexHeader(
                    title = "Game",
                    backgroundImageUrl = "..."
                )
            }
        }
    }
}
