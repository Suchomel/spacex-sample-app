package cz.gemsi.spacex.feature.launch.system

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import cz.gemsi.spacex.feature.launch.domain.RocketLaunchController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.math.atan2

class RocketLaunchDelegate(
    private val sensorManager: SensorManager,
) : SensorEventListener, RocketLaunchController, CoroutineScope {

    private var accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val pickUpEvents = MutableSharedFlow<Unit>()

    override fun observePickUpEvent(): Flow<Unit> {
        return pickUpEvents.asSharedFlow()
    }

    override fun registerListener() {
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun unregisterListener() {
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        launch {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                val y = event.values[1]
                val z = event.values[2]

                val pitch = atan2(y.toDouble(), z.toDouble())

                val pitchDegrees = Math.toDegrees(pitch)

                if (pitchDegrees > 60) {
                    pickUpEvents.emit(Unit)
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.IO
}