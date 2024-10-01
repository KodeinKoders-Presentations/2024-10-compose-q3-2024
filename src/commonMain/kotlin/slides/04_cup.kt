package slides

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import net.kodein.cup.Slide
import net.kodein.theme.compose.m2.Link
import org.jetbrains.compose.resources.painterResource
import state_of_compose_2024.generated.resources.Res
import state_of_compose_2024.generated.resources.cup


@Composable
private fun RowScope.SlowAnimatedVisibility(
    visible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = expandHorizontally(tween(1500)) + fadeIn(tween(1500)),
        exit = shrinkHorizontally(tween(1500)) + fadeOut(tween(1500)),
    ) {
        content()
    }
}

@Composable
private fun ColumnScope.SlowAnimatedVisibility(
    visible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(tween(1500)) + fadeIn(tween(1500)),
        exit = shrinkVertically(tween(1500)) + fadeOut(tween(1500)),
    ) {
        content()
    }
}

val CuP by Slide(
    stepCount = 2
) { step ->
    Image(
        painter = painterResource(Res.drawable.cup),
        contentDescription = "Compose ur Pres",
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(40.dp))
    )

    Spacer(Modifier.height(16.dp))

    Row {
        ProvideTextStyle(
            MaterialTheme.typography.h1
        ) {
            Text("C")
            SlowAnimatedVisibility(step == 0) {
                Text("ompose ")
            }
            Text("u")
            SlowAnimatedVisibility(step == 0) {
                Text("r ")
            }
            Text("P")
            SlowAnimatedVisibility(step == 0) {
                Text("res!")
            }
        }
    }

    SlowAnimatedVisibility(step >= 1) {
        Link("github.com/KodeinKoders/CuP", "https://github.com/KodeinKoders/CuP")
    }
}