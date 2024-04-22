package cz.gemsi.spacex.library.api.data

import com.google.gson.annotations.SerializedName

typealias RocketsDto = List<RocketDto>

data class RocketDto(
    val id: Long,
    val active: Boolean,
    val stages: Long,
    val boosters: Long,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Long,
    @SerializedName("success_rate_pct")
    val successRatePct: Long,
    @SerializedName("first_flight")
    val firstFlight: String,
    val country: String,
    val company: String,
    val height: HeightDto,
    val diameter: DiameterDto,
    val mass: MassDto,
    @SerializedName("payload_weights")
    val payloadWeights: List<PayloadWeightDto>,
    @SerializedName("first_stage")
    val firstStage: FirstStageDto,
    @SerializedName("second_stage")
    val secondStage: SecondStageDto,
    val engines: Engines,
    @SerializedName("landing_legs")
    val landingLegs: LandingLegsDto,
    val wikipedia: String,
    val description: String,
    @SerializedName("rocket_id")
    val rocketId: String,
    @SerializedName("rocket_name")
    val rocketName: String,
    @SerializedName("rocket_type")
    val rocketType: String,
    @SerializedName("flickr_images")
    val flickrImages: List<String> = listOf(),
)

data class HeightDto(
    val meters: Double,
    val feet: Double,
)

data class DiameterDto(
    val meters: Double,
    val feet: Double,
)

data class MassDto(
    val kg: Long,
    val lb: Long,
)

data class PayloadWeightDto(
    val id: String,
    val name: String,
    val kg: Long,
    val lb: Long,
)

data class FirstStageDto(
    val reusable: Boolean,
    val engines: Int,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Double,
    @SerializedName("burn_time_sec")
    val burnTimeSec: Long,
    @SerializedName("thrust_sea_level")
    val thrustSeaLevel: ThrustSeaLevelDto,
    @SerializedName("thrust_vacuum")
    val thrustVacuum: ThrustVacuumDto,
    val cores: Long?,
)

data class ThrustSeaLevelDto(
    val kN: Long,
    val lbf: Long,
)

data class ThrustVacuumDto(
    val kN: Long,
    val lbf: Long,
)

data class SecondStageDto(
    val reusable: Boolean,
    val engines: Int,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Double?,
    @SerializedName("burn_time_sec")
    val burnTimeSec: Long,
    val thrust: ThrustDto,
    val payloads: PayloadsDto,
)

data class ThrustDto(
    val kN: Long,
    val lbf: Long,
)

data class PayloadsDto(
    @SerializedName("option_1")
    val option1: String,
    @SerializedName("composite_fairing")
    val compositeFairing: CompositeFairingDto,
    @SerializedName("option_2")
    val option2: String?,
)

data class CompositeFairingDto(
    val height: HeightDto,
    val diameter: DiameterDto,
)

data class Engines(
    val number: Long,
    val type: String,
    val version: String,
    val layout: String?,
    @SerializedName("engine_loss_max")
    val engineLossMax: Long?,
    @SerializedName("propellant_1")
    val propellant1: String,
    @SerializedName("propellant_2")
    val propellant2: String,
    @SerializedName("thrust_sea_level")
    val thrustSeaLevel: ThrustSeaLevelDto,
    @SerializedName("thrust_vacuum")
    val thrustVacuum: ThrustVacuumDto,
    @SerializedName("thrust_to_weight")
    val thrustToWeight: Double?,
)


data class LandingLegsDto(
    val number: Long,
    val material: String?,
)
