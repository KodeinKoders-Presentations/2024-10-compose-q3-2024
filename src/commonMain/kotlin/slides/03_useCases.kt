package slides

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import net.kodein.cup.PreparedSlide
import net.kodein.cup.Slide
import net.kodein.cup.Slides
import net.kodein.cup.speaker.isInSpeakerWindow
import net.kodein.cup.widgets.material.BulletPoints
import net.kodein.theme.KodeinColors
import net.kodein.theme.compose.Color


@Composable
private fun rotateInt(mod: Int, delay: Long): Int {
    var step by remember { mutableStateOf(0) }

    LaunchedEffect(mod, delay) {
        step = 0
        while (true) {
            delay(delay)
            step = (step + 1) % mod
        }
    }

    return step
}

@Composable
fun Rotate(
    visible: Boolean,
    content: @Composable () -> Unit
) {
    var direction by remember { mutableStateOf(1f) }
    val density = LocalDensity.current
    val anim by animateFloatAsState(targetValue = if (visible) 1f else 0f, tween(600))

    SideEffect {
        when (anim) {
            0f -> direction = -1f
            1f -> direction = 1f
        }
    }

    Box(
        modifier = Modifier
            .graphicsLayer {
                alpha = anim
                translationY = with(density) { 16.dp.toPx() * (1f - anim) * direction }
                clip = false
            }
    ) {
        content()
    }
}

private val UCRoleQuestion by Slide {
    Text(
        text = "Is that it then?",
        style = MaterialTheme.typography.h1
    )

    Spacer(Modifier.height(8.dp))

    val platform = rotateInt(4, 1600)

    ProvideTextStyle(MaterialTheme.typography.subtitle1) {
        Row {
            Text("Is the role of ")
            Box(
                contentAlignment = Alignment.CenterEnd,
            ) {
                Rotate(platform == 0) {
                    Text("Android", fontWeight = Bold)
                }
                Rotate(platform == 1) {
                    Text("iOS", fontWeight = Bold)
                }
                Rotate(platform == 2) {
                    Text("Desktop", fontWeight = Bold)
                }
                Rotate(platform == 3) {
                    Text("Web", fontWeight = Bold)
                }
            }
            Text(" developper", fontWeight = Bold)
            Text(" dead?")
        }
    }
}

private val UserExperience by Slide(
    stepCount = 3
) { step ->
    val rust = Color(KodeinColors.rust)
    val copper = Color(KodeinColors.copper)
    val purple = Color(KodeinColors.purple)

    Stack(
        title = {},
        modifier = Modifier
            .fillMaxWidth()
    ) {
        var rowWidth: Dp? by remember { mutableStateOf(null) }
        val density = LocalDensity.current
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { with(density) { rowWidth = it.width.toDp() } }
        ) {
            Layer(
                name = "User interface",
                color = lerp(copper, purple, animateFloatAsState(if (step >= 2) 1f else 0f, tween(1000)).value),
                modifier = Modifier.weight(1f)
            )
            Layer(
                name = "Platform Integration",
                color = purple,
                modifier =
                    if (rowWidth != null) Modifier.width(
                        animateDpAsState(if (step >= 1) rowWidth!! / 2 else 0.dp, tween(1000)).value
                    )
                    else Modifier
            )
        }
        Layer("Behaviour", lerp(rust, copper, 0.666f), Modifier.fillMaxWidth())
        Layer("Business Logic", lerp(rust, copper, 0.333f), Modifier.fillMaxWidth())
        Layer("Input / Output", rust, Modifier.fillMaxWidth())
    }
}

@Composable
private fun Hidden(
    isHidden: Boolean,
    onReveal: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier) {
        content()
        Box(
            Modifier
                .matchParentSize()
                .alpha(
                    animateFloatAsState(
                        targetValue = if (isHidden) {
                            if (isInSpeakerWindow()) 0.5f else 1f
                        } else {
                            0f
                        },
                        animationSpec = tween(1000)
                    ).value
                )
                .background(Color(KodeinColors.copper))
                .clickable { onReveal() }
                .pointerHoverIcon(PointerIcon.Hand)
        )
    }

}

private val ValidUseCases by PreparedSlide {

    val hidden = remember {
        mutableStateMapOf(
            "poc" to true,
            "desktop" to true,
            "data" to true,
            "themed" to true,
            "cup" to true,
        )
    }

    slideContent {
        Text(
            text = "When should you use\nCompose Multiplatform?",
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(16.dp))

        BulletPoints {
            BulletPoint {
                Text("For ")
                Hidden(
                    isHidden = hidden["poc"]!!,
                    onReveal = { hidden["poc"] = false },
                ) { Text("proof of concepts") }
            }
            BulletPoint {
                Text("For ")
                Hidden(
                    isHidden = hidden["desktop"]!!,
                    onReveal = { hidden["desktop"] = false },
                ) { Text("desktop apps") }
            }
            BulletPoint {
                Text("For ")
                Hidden(
                    isHidden = hidden["data"]!!,
                    onReveal = { hidden["data"] = false },
                ) { Text("professional / data apps") }
            }
            BulletPoint {
                Text("For ")
                Hidden(
                    isHidden = hidden["themed"]!!,
                    onReveal = { hidden["themed"] = false },
                ) { Text("highly themed apps") }
            }
            BulletPoint {
                Text("For ")
                Hidden(
                    isHidden = hidden["cup"]!!,
                    onReveal = { hidden["cup"] = false },
                ) { Text("presentations!") }
            }
        }
    }
}

val UseCases = Slides(
    UCRoleQuestion,
    UserExperience,
    ValidUseCases,
)