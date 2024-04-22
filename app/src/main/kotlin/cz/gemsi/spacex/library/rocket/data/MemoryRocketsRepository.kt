package cz.gemsi.spacex.library.rocket.data

import cz.gemsi.spacex.library.rocket.domain.LocalRocketsRepository
import cz.gemsi.spacex.library.rocket.model.Rocket

class MemoryRocketsRepository : LocalRocketsRepository {

    override var selectedRocketDetail : Rocket? = null
}