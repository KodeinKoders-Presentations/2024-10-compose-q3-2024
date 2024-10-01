package slides

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.kodein.cup.SLIDE_SIZE_16_10
import net.kodein.cup.Slide
import net.kodein.cup.SlideSpecs
import net.kodein.theme.KodeinColors
import net.kodein.theme.compose.Color
import org.jetbrains.compose.resources.painterResource
import state_of_compose_2024.generated.resources.Res
import state_of_compose_2024.generated.resources.illus_cat


val HowItWorks by Slide(
    specs = SlideSpecs(size = SLIDE_SIZE_16_10)
) {
    Box(
        Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.illus_cat),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(32.dp))
        )

        with(LocalDensity.current) {
            Text(
                text = "Booooored...",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(KodeinColors.orange_light),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .graphicsLayer(translationX = (-3).dp.toPx(), translationY =  88.dp.toPx())
            )

            Text(
                text = "Show me some code!",
                fontSize = 42.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(KodeinColors.orange_light),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .graphicsLayer(translationX = (-3).dp.toPx(), translationY =  (-64).dp.toPx())
            )
        }
    }
}