package cz.gemsi.spacex.library.navigation.domain

import cz.gemsi.spacex.core.model.UnitUseCase

class GoBackUseCase(
    private val navigationRepository: NavigationRepository
) : UnitUseCase<Unit> {

    override fun invoke() {
        navigationRepository.goBack()
    }
}