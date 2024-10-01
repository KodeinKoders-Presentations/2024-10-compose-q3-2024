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
import net.kodein.cup.Slide
import net.kodein.theme.KodeinColors
import net.kodein.theme.compose.Color
import org.jetbrains.compose.resources.painterResource
import state_of_compose_2024.generated.resources.Res
import state_of_compose_2024.generated.resources.illus_router_viewmodels


val RouterAndViewModels by Slide {
    Box(
        Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.illus_router_viewmodels),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(32.dp))
        )

        with(LocalDensity.current) {
            Text(
                text = "Navigation & ViewModels",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(KodeinColors.light_purple),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .graphicsLayer(translationX = (-3).dp.toPx(), translationY =  32.dp.toPx())
            )
        }
    }
}
