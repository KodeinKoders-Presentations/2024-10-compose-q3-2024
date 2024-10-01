import net.kodein.cup.Slides
import net.kodein.cup.cupApplication
import net.kodein.theme.cup.KodeinPresentation
import net.kodein.theme.cup.slides.kodeinActivities
import slides.*


fun main() = cupApplication(
    title = "State of Compose/Multiplatform (late 2024)"
) {
    KodeinPresentation(
        slides = presentationSlides,
    )
}

val presentationSlides = Slides(
    Intro,
    Share,
    HowItWorks,
    UseCases,
    CuP,
    RouterAndViewModels,
    Limits,
    kodeinActivities,
    Outro
)
