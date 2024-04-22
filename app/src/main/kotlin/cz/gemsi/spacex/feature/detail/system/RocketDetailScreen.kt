package cz.gemsi.spacex.feature.detail.system

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

@Composable
fun RocketDetailScreen() {
    RocketDetailScreenContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RocketDetailScreenContent() {
    Column {
        CenterAlignedTopAppBar(
            title = { Text("Toolbar") },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground,
            )
        )
    }
}