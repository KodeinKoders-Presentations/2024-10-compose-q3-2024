package slides

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.kodein.cup.Slide
import net.kodein.theme.KodeinColors
import net.kodein.theme.compose.Color


@Composable
fun Stack(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier
            .padding(4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            title()
        }
        content()
    }
}

@Composable
fun Layer(
    name: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        textAlign = TextAlign.Center,
        color = Color(KodeinColors.light),
        maxLines = 1,
        softWrap = false,
        modifier = modifier
            .padding(4.dp)
            .background(color, RoundedCornerShape(25))
            .padding(horizontal = 4.dp)
    )
}

@Composable
private fun Modifier.animateAlpha(target: Float): Modifier {
    val alpha by animateFloatAsState(target, tween(1000))
    return alpha(alpha)
}

@Composable
private fun RowScope.TitleAnimatedVisibility(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable() AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(1000)) + expandHorizontally(tween(1000)),
        exit = fadeOut(tween(1000)) + shrinkHorizontally(tween(1000)),
        modifier = modifier,
        content = content
    )
}

val Share by Slide(
    stepCount = 6
) { step ->
    val rust = Color(KodeinColors.rust)
    val copper = Color(KodeinColors.copper)
    val zinzolin = Color(KodeinColors.zinzolin)
    val purple = Color(KodeinColors.purple)

    Row(
        modifier = Modifier.padding(bottom = 32.dp)
    ) {
        ProvideTextStyle(MaterialTheme.typography.h4) {
            TitleAnimatedVisibility(step >= 2) {
                Row {
                    Text("Kotlin ")
                    TitleAnimatedVisibility(step >= 5) {
                        Text("& Compose ")
                    }
                    Text("Multiplatform ")
                }
            }
            Text("Application")
        }
    }

    Box {
        Row {
            listOf("Android", "iOS", "Desktop / Web").forEach {
                Stack(
                    title = { Text(it) },
                    modifier = Modifier
                        .weight(1f)
                        .animateAlpha(if (step >= 1) 1f else 0f)
                ) {
                    Layer("User interface", purple, Modifier.fillMaxWidth().animateAlpha(if (step < 5) 1f else 0f))
                    Layer("Behaviour", lerp(zinzolin, purple, 0.666f), Modifier.fillMaxWidth().animateAlpha(if (step < 4) 1f else 0f))
                    Layer("Business Logic", lerp(zinzolin, purple, 0.333f), Modifier.fillMaxWidth().animateAlpha(if (step < 2) 1f else 0f))
                    Layer("Input / Output", zinzolin, Modifier.fillMaxWidth().animateAlpha(if (step < 3) 1f else 0f))
                }
            }
        }
        Stack(
            title = { Text("") },
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Layer("User interface", copper, Modifier.fillMaxWidth().animateAlpha(if (step < 1 || step >= 5) 1f else 0f))
            Layer("Behaviour", lerp(rust, copper, 0.666f), Modifier.fillMaxWidth().animateAlpha(if (step < 1 || step >= 4) 1f else 0f))
            Layer("Business Logic", lerp(rust, copper, 0.333f), Modifier.fillMaxWidth().animateAlpha(if (step < 1 || step >= 2) 1f else 0f))
            Layer("Input / Output", rust, Modifier.fillMaxWidth().animateAlpha(if (step < 1 || step >= 3) 1f else 0f))
        }
    }
}
