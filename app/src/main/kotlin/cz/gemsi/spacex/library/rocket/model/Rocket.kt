package cz.gemsi.spacex.library.rocket.model

import java.time.LocalDate

data class Rocket(
    val id: Long,
    val firstFlight: LocalDate,
    val description: String,
    val rocketName: String,
    val height: Height,
    val diameter: Diameter,
    val mass: Mass,
    val firstStage: Stage,
    val secondStage: Stage,
    val images: List<String>,
)

data class Height(
    val meters: Double,
    val feet: Double,
)

data class Diameter(
    val meters: Double,
    val feet: Double,
)

data class Mass(
    val kg: Long,
    val lb: Long,
)

data class Stage(
    val reusable: Boolean,
    val engines: Int,
    val fuelAmountTons: Double?,
    val burnTimeSec: Long,
)
