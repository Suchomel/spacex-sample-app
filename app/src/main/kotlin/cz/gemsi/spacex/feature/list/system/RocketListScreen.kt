package cz.gemsi.spacex.feature.list.system

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.gemsi.spacex.R
import cz.gemsi.spacex.feature.list.presentation.RocketListViewModel
import cz.gemsi.spacex.library.date.system.mediumFormat
import cz.gemsi.spacex.library.rocket.model.Rocket
import cz.gemsi.spacex.library.theme.system.Dimensions
import org.koin.androidx.compose.getViewModel
import java.time.LocalDate

@Composable
fun RocketListScreen() {
    val viewModel = getViewModel<RocketListViewModel>()
    val state = viewModel.states.collectAsState()

    RocketListScreenContent(
        state = state.value,
        onRocketClick = viewModel::onRocketClick,
        onRefreshData = viewModel::onRefreshData,
    )
}

@Composable
fun RocketListScreenContent(
    state: RocketListViewModel.State,
    onRocketClick: (Rocket) -> Unit,
    onRefreshData: () -> Unit,
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
                    when {
                        state.rockets.isNotEmpty() && !state.isLoading -> {
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

                        state.isError -> {
                            item {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .padding(Dimensions.spaceXL)
                                        .fillMaxWidth()
                                ) {
                                    Spacer(modifier = Modifier.padding(Dimensions.spaceS))
                                    Text(text = stringResource(R.string.rocketList_failedToLoadData_errorMessage))
                                    Spacer(modifier = Modifier.padding(Dimensions.spaceS))
                                    Button(
                                        onClick = onRefreshData,
                                        enabled = !state.isLoading,
                                    ) {
                                        Text(text = stringResource(R.string.rocketList_retry_button))
                                    }
                                }
                            }
                        }

                        state.isLoading -> {
                            items(4) { index ->
                                if (index != 0) {
                                    Divider(
                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f),
                                        modifier = Modifier.padding(horizontal = Dimensions.spaceM)
                                    )
                                }

                                LoadingRocketListItem()
                            }
                        }
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
            Text(text = rocket.rocketName, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(Dimensions.spaceXXS))
            Text(
                text = stringResource(
                    R.string.rocketList_firstFlight_text,
                    rocket.firstFlight.mediumFormat()
                ),
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

@Composable
fun LoadingRocketListItem() {
    val skeletonColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .defaultMinSize(minHeight = Dimensions.minButtonHeight)
            .padding(horizontal = Dimensions.spaceM, vertical = Dimensions.spaceS)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_rocket),
            tint = Color.Transparent,
            contentDescription = null,
            modifier = Modifier.background(skeletonColor, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(Dimensions.spaceM))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.rocketList_rocketName_loadingPlaceholder),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Transparent,
                modifier = Modifier.background(skeletonColor, shape = RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.height(Dimensions.spaceXXS))
            Text(
                text = stringResource(
                    R.string.rocketList_firstFlight_text,
                    LocalDate.now().mediumFormat()
                ),
                color = Color.Transparent,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.background(skeletonColor, shape = RoundedCornerShape(4.dp))
            )
        }
    }
}