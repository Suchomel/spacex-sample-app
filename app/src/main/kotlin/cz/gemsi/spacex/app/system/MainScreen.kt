package cz.gemsi.spacex.app.system

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.gemsi.spacex.app.presentation.MainViewModel
import cz.gemsi.spacex.feature.detail.system.RocketDetailScreen
import cz.gemsi.spacex.feature.launch.system.RocketLaunchScreen
import cz.gemsi.spacex.feature.list.system.RocketListScreen
import cz.gemsi.spacex.library.navigation.model.NavigationEvent
import cz.gemsi.spacex.library.navigation.model.Route
import cz.gemsi.spacex.library.theme.system.SpacexTheme
import org.koin.androidx.compose.getViewModel


@Composable
fun MainScreen() {
    val viewModel = getViewModel<MainViewModel>()
    val state = viewModel.states.collectAsState()

    MainScreenContent(
        state = state.value,
        onConsumeNavigationEvent = viewModel::onConsumeNavigationEvent,
    )
}

@Composable
fun MainScreenContent(
    state: MainViewModel.State,
    onConsumeNavigationEvent: () -> Unit = {},
) {
    val navController = rememberNavController()

    SpacexTheme {
        NavigationEffect(state, onConsumeNavigationEvent, navController)
        NavigationHost(navController)
    }
}

@Composable
private fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.RocketList(),
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(Route.RocketList()) { RocketListScreen() }
        composable(Route.RocketDetail()) { RocketDetailScreen() }
        composable(Route.RocketLaunch()) { RocketLaunchScreen() }
    }
}

@Composable
private fun NavigationEffect(
    state: MainViewModel.State,
    onConsumeNavigationEvent: () -> Unit = {},
    navController: NavHostController
) {
    val eventState = state.navigationEvent?.collectAsState()
    val navigationEvent = eventState?.value

    SideEffect {
        when (navigationEvent) {
            NavigationEvent.GoBack -> {
                navController.navigateUp()
                onConsumeNavigationEvent()
            }

            is NavigationEvent.GoTo -> {
                navController.navigate(navigationEvent.route())
                onConsumeNavigationEvent()
            }

            null -> Unit
        }
    }
}
