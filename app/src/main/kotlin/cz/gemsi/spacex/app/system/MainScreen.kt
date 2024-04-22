package cz.gemsi.spacex.app.system

import androidx.compose.runtime.Composable
import cz.gemsi.spacex.feature.list.system.RocketListScreen
import cz.gemsi.spacex.ui.theme.SpacexTheme

@Composable
fun MainScreen() {
    MainScreenContent()
}

@Composable
fun MainScreenContent() {
    SpacexTheme {
        RocketListScreen()
    }
}