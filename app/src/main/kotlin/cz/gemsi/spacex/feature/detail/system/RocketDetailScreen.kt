package cz.gemsi.spacex.feature.detail.system

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import cz.gemsi.spacex.R
import cz.gemsi.spacex.feature.detail.presentation.RocketDetailViewModel
import cz.gemsi.spacex.library.design.system.SpaceXToolbar
import cz.gemsi.spacex.library.rocket.model.Rocket
import cz.gemsi.spacex.library.rocket.model.Stage
import cz.gemsi.spacex.library.theme.system.Dimensions
import org.koin.androidx.compose.getViewModel
import kotlin.math.roundToInt

@Composable
fun RocketDetailScreen() {
    val viewModel = getViewModel<RocketDetailViewModel>()
    val state = viewModel.states.collectAsState()

    RocketDetailScreenContent(
        state = state.value,
        onGoBack = viewModel::onGoBack,
        onLaunchClick = viewModel::onLaunchClick,
    )
}

@Composable
private fun RocketDetailScreenContent(
    state: RocketDetailViewModel.State,
    onGoBack: () -> Unit,
    onLaunchClick: () -> Unit,
) {
    val rocket = state.rocket ?: return

    Surface {
        Column {
            SpaceXToolbar(
                title = rocket.rocketName,
                onGoBack = onGoBack,
                actions = {
                    TextButton(onClick = onLaunchClick) {
                        Text(text = stringResource(R.string.rocketDetail_launch_button))
                    }
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(Dimensions.screenPadding)

            ) {
                OverviewSection(rocket.description)
                ParametersSection(rocket)
                StagesSection(rocket)
                ImagesSection(rocket.images)
            }
        }
    }
}

@Composable
private fun OverviewSection(description: String) {
    Text(
        text = stringResource(R.string.rocketDetail_overview_title),
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.height(Dimensions.spaceS))
    Text(text = description, style = MaterialTheme.typography.bodyMedium)
}

@Composable
private fun ParametersSection(rocket: Rocket) {
    Spacer(modifier = Modifier.height(Dimensions.spaceL))
    Text(
        text = stringResource(R.string.rocketDetail_parameters_title),
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.height(Dimensions.spaceS))
    Row {
        val modifier = Modifier.weight(1f)
        ParameterCard(
            value = stringResource(
                R.string.rocketDetail_meterValue_label,
                rocket.height.meters.roundToInt()
            ),
            name = stringResource(R.string.rocketDetail_height_label),
            modifier = modifier
        )
        Spacer(modifier = Modifier.width(Dimensions.spaceL))
        ParameterCard(
            value = stringResource(
                R.string.rocketDetail_meterValue_label,
                rocket.diameter.meters.roundToInt()
            ),
            name = stringResource(R.string.rocketDetail_diameter_label),
            modifier = modifier
        )
        Spacer(modifier = Modifier.width(Dimensions.spaceL))
        ParameterCard(
            value = stringResource(
                R.string.rocketDetail_tunaValue_label,
                rocket.mass.kg / 1000
            ),
            name = stringResource(R.string.rocketDetail_mass_label),
            modifier = modifier
        )
    }
}

@Composable
private fun ParameterCard(
    value: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier.aspectRatio(1f)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = value, style = MaterialTheme.typography.headlineMedium)
            Text(text = name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
private fun StagesSection(rocket: Rocket) {
    Spacer(modifier = Modifier.height(Dimensions.spaceL))
    StageSection(
        name = stringResource(R.string.rocketDetail_firstStage_title),
        stage = rocket.firstStage
    )
    Spacer(modifier = Modifier.height(Dimensions.spaceL))
    StageSection(
        name = stringResource(R.string.rocketDetail_secondStage_title),
        stage = rocket.secondStage
    )
}

@Composable
private fun StageSection(
    name: String,
    stage: Stage,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(Dimensions.spaceM)) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(Dimensions.spaceS))
            StageParameterItem(
                icon = R.drawable.ic_reusable,
                text = stringResource(id = if (stage.reusable) R.string.rocketDetail_reusable_label else R.string.rocketDetail_notReusable_label),
            )
            StageParameterItem(
                icon = R.drawable.ic_engine,
                text = pluralStringResource(
                    id = R.plurals.rocketDetail_enginesCount_label,
                    count = stage.engines,
                    stage.engines
                )
            )
            stage.fuelAmountTons?.let {
                StageParameterItem(
                    icon = R.drawable.ic_fuel,
                    text = stringResource(
                        id = R.string.rocketDetail_tonsOfFuel_label,
                        it.roundToInt()
                    ),
                )
            }
            StageParameterItem(
                icon = R.drawable.ic_burn,
                text = stringResource(id = R.string.rocketDetail_burnTime_label, stage.burnTimeSec),
            )
        }
    }
}

@Composable
private fun StageParameterItem(
    @DrawableRes icon: Int,
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = Dimensions.spaceXS)
    ) {
        Icon(
            painter = painterResource(id = icon),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(Dimensions.spaceM))
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ImagesSection(
    images: List<String>,
) {
    if (images.isEmpty()) return

    Spacer(modifier = Modifier.height(Dimensions.spaceL))
    Text(
        text = stringResource(R.string.rocketDetail_photos_title),
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.height(Dimensions.spaceS))
    images.forEachIndexed { index, url ->
        if (index != 0) Spacer(modifier = Modifier.height(Dimensions.spaceM))

        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
            modifier = Modifier.fillMaxWidth()
        ) {
            GlideImage(
                model = url,
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
        }
    }
}