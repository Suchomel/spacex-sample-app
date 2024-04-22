package cz.gemsi.spacex.feature.list.system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cz.gemsi.spacex.R
import cz.gemsi.spacex.feature.list.presentation.RocketListViewModel
import cz.gemsi.spacex.library.rocket.Rocket
import cz.gemsi.spacex.library.theme.system.Dimensions
import org.koin.androidx.compose.getViewModel

@Composable
fun RocketListScreen() {
    val viewModel = getViewModel<RocketListViewModel>()
    val state = viewModel.states.collectAsState()

    RocketListScreenContent(
        state = state.value,
        onRocketClick = viewModel::onRocketClick
    )
}

@Composable
fun RocketListScreenContent(
    state: RocketListViewModel.State,
    onRocketClick: (Rocket) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.screenPadding)
        ) {
            Text(
                text = stringResource(R.string.rocketList_toolbar_title),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(Dimensions.spaceM))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                )
            ) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    itemsIndexed(state.rockets) { index, rocket ->
                        if (index != 0) {
                            Divider(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                                modifier = Modifier.padding(horizontal = Dimensions.spaceM)
                            )
                        }

                        RocketListItem(
                            rocket = rocket,
                            onClick = { onRocketClick(rocket) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RocketListItem(rocket: Rocket, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .defaultMinSize(minHeight = Dimensions.minButtonHeight)
            .clickable { onClick() }
            .padding(horizontal = Dimensions.spaceM, vertical = Dimensions.spaceS)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_rocket),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(Dimensions.spaceM))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = rocket.name, style = MaterialTheme.typography.titleMedium)
            Text(
                text = stringResource(R.string.rocketList_firstFlight_text, rocket.firstFlight),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.width(Dimensions.spaceM))
        Icon(
            painter = painterResource(id = R.drawable.ic_chevron_right),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
            contentDescription = null
        )
    }
}