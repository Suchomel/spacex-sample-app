package cz.gemsi.spacex.feature.launch.system

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import cz.gemsi.spacex.R
import cz.gemsi.spacex.core.system.LifecycleEffect
import cz.gemsi.spacex.feature.launch.presentation.RocketLaunchViewModel
import cz.gemsi.spacex.library.design.system.SpaceXToolbar
import org.koin.androidx.compose.getViewModel

@Composable
fun RocketLaunchScreen() {
    val viewModel = getViewModel<RocketLaunchViewModel>()
    val state = viewModel.states.collectAsState()

    LifecycleEffect(
        onResume = viewModel::onResume,
        onPause = viewModel::onPause,
    )

    RocketLaunchScreenContent(
        state = state.value,
        onGoBack = viewModel::onGoBack,
    )
}

@Composable
private fun RocketLaunchScreenContent(
    state: RocketLaunchViewModel.State,
    onGoBack: () -> Unit,
) {
    val weight = animateFloatAsState(
        targetValue = if (state.isLaunched) 1f else 0f, label = "",
        animationSpec = spring(
            stiffness = 50f
        )
    )

    Surface {
        Column {
            SpaceXToolbar(
                title = stringResource(id = R.string.rocketLaunch_toolbar_title),
                onGoBack = onGoBack,
            )

            Column {
                if (weight.value != 1f) Spacer(modifier = Modifier.weight(1f - weight.value))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_rocket_idle),
                        tint = Color.Unspecified,
                        contentDescription = null
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_rocket_flying),
                        tint = Color.Unspecified,
                        contentDescription = null,
                        modifier = Modifier.alpha(weight.value)
                    )
                }
                if (weight.value != 0f) Spacer(modifier = Modifier.weight(weight.value))
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = if (state.isLaunched) R.string.rocketLaunch_rocketSuccess_message else R.string.rocketLaunch_rocketIdle_message),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(0.6f)
                    )
                }
            }
        }
    }
}