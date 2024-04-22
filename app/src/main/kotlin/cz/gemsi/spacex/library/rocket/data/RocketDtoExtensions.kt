package cz.gemsi.spacex.library.rocket.data

import cz.gemsi.spacex.library.api.data.DiameterDto
import cz.gemsi.spacex.library.api.data.FirstStageDto
import cz.gemsi.spacex.library.api.data.HeightDto
import cz.gemsi.spacex.library.api.data.MassDto
import cz.gemsi.spacex.library.api.data.RocketDto
import cz.gemsi.spacex.library.api.data.SecondStageDto
import cz.gemsi.spacex.library.rocket.model.Diameter
import cz.gemsi.spacex.library.rocket.model.Height
import cz.gemsi.spacex.library.rocket.model.Mass
import cz.gemsi.spacex.library.rocket.model.Rocket
import cz.gemsi.spacex.library.rocket.model.Stage
import java.time.LocalDate

fun RocketDto.toModel() = Rocket(
    id = id,
    firstFlight = LocalDate.parse(firstFlight),
    description = description,
    rocketName = rocketName,
    height = height.toModel(),
    diameter = diameter.toModel(),
    mass = mass.toModel(),
    firstStage = firstStage.toModel(),
    secondStage = secondStage.toModel(),
    images = flickrImages,
)

private fun HeightDto.toModel() = Height(
    meters = meters,
    feet = feet,
)

private fun DiameterDto.toModel() = Diameter(
    meters = meters,
    feet = feet,
)

private fun MassDto.toModel() = Mass(
    kg = kg,
    lb = lb,
)

private fun FirstStageDto.toModel() = Stage(
    reusable = reusable,
    engines = engines,
    fuelAmountTons = fuelAmountTons,
    burnTimeSec = burnTimeSec,
)

private fun SecondStageDto.toModel() = Stage(
    reusable = reusable,
    engines = engines,
    fuelAmountTons = fuelAmountTons,
    burnTimeSec = burnTimeSec,
)