package slides

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.kodein.cup.Slide
import net.kodein.theme.cup.kStyled
import net.kodein.theme.cup.ui.KodeinLogo


val Outro by Slide {

    Text(
        text = "Thank you!",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h1
    )

    Text(
        text = kStyled { "${+m}Meetup${-m}" },
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.subtitle2,
        fontWeight = FontWeight.Light
    )

    Spacer(Modifier.height(16.dp))

    Text(
        text = "Salomon BRYS",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.primary
    )

    KodeinLogo("koders", Modifier.height(64.dp)) {}

    Spacer(Modifier.height(32.dp))

    LinksToThisPresentation()
}
