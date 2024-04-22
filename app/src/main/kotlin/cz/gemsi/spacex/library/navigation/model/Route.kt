package cz.gemsi.spacex.library.navigation.model

enum class Route {
    RocketList,
    RocketDetail,
    RocketLaunch;

    operator fun invoke() = name
}