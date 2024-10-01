package slides

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.kodein.cup.Slide
import net.kodein.cup.widgets.material.BulletPoints


val Limits by Slide {
    Text(
        text = "Limits:",
        style = MaterialTheme.typography.h1
    )

    Spacer(Modifier.height(16.dp))

    BulletPoints {
        BulletPoint {
            Text("No iOS Look & Feel")
        }
        BulletPoint {
            Text("No Swift interop (iOS devs will hate you)")
        }
        BulletPoint {
            Text("No reflection")
        }
        BulletPoint {
            Text("Limited ecosystem")
        }
        BulletPoint {
            Text("IDE support inconsistency")
        }
        BulletPoint {
            Text("Incompatibilities between versions with weird errors")
        }
        BulletPoint {
            Text("Kotlin/Wasm in ALPHA")
        }
    }

}
