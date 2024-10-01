package slides

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.alexzhirkevich.qrose.options.*
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import net.kodein.cup.SLIDE_SIZE_16_9
import net.kodein.cup.Slide
import net.kodein.cup.SlideSpecs
import net.kodein.cup.Slides
import net.kodein.theme.KodeinColors
import net.kodein.theme.compose.Color
import net.kodein.theme.compose.m2.Link
import net.kodein.theme.cup.ui.KodeinLogo
import org.jetbrains.compose.resources.painterResource
import state_of_compose_2024.generated.resources.Res
import state_of_compose_2024.generated.resources.illus_phone
import state_of_compose_2024.generated.resources.illus_silverbullet
import kotlin.math.min


@Composable
fun LinksToThisPresentation() {
    Link(
        uri = "https://p.kodein.net/compose-q3-2024"
    ) {
        Text(
            text = "https://p.kodein.net/compose-q3-2024",
            style = MaterialTheme.typography.caption,
            color = Color(KodeinColors.coral)
        )
    }
    Image(
        painter = rememberQrCodePainter(
            "https://p.kodein.net/compose-q3-2024",
            shapes = QrShapes(
                ball = QrBallShape.roundCorners(.25f),
                frame = QrFrameShape.roundCorners(.25f),
                darkPixel = QrPixelShape.circle(),
            ),
            colors = QrColors(
                dark = QrBrush.solid(Color(KodeinColors.coral))
            )
        ),
        contentDescription = "This presentation",
        modifier = Modifier.padding(8.dp)
    )
}

val Title by Slide(
    specs = SlideSpecs(
        size = SLIDE_SIZE_16_9
    ),
    stepCount = 2
) { step ->

    val scale by rememberInfiniteTransition().animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(tween(2_500), RepeatMode.Reverse)
    )

    KodeinLogo(
        division = "koders",
        modifier = Modifier
            .height(64.dp)
            .scale(scale)
    ) {}

    Spacer(Modifier.height(16.dp))

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 64.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            Text(
                text = "State of Compose Multiplatform",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1
            )

            val highlight by animateFloatAsState(if (step >= 1) 1f else 0.0f, tween(1000))

            Text(
                text = "(Q3 2024)",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .scale(1f + 0.4f * highlight)
                    .padding(4.dp * highlight)
                    .border(4.dp * highlight, lerp(Transparent, Color(KodeinColors.orange), min(highlight * 2, 1f)))
                    .padding(4.dp + 4.dp * highlight)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Salomon BRYS",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1,
                color = Color(KodeinColors.salmon)
            )

            Spacer(Modifier.height(16.dp))

            LinksToThisPresentation()
        }
        Image(
            painter = painterResource(Res.drawable.illus_phone),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
        )
    }
}

val SilverBullet by Slide {
    Image(
        painter = painterResource(Res.drawable.illus_silverbullet),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
}

val Intro = Slides(
    Title,
    SilverBullet
)