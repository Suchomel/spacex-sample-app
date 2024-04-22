package cz.gemsi.spacex.library.rocket.domain

import cz.gemsi.spacex.library.rocket.model.Rocket

interface LocalRocketsRepository {

    var selectedRocketDetail: Rocket?
}
