package cz.gemsi.spacex.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class AbstractViewModel<S : AbstractViewModel.State>(initialState: S) : ViewModel() {

    private val mutableStates = MutableStateFlow(initialState)
    val states: StateFlow<S> = mutableStates.asStateFlow()

    private val isActive = MutableStateFlow(false)

    private val scope: CoroutineScope = MainScope() + SupervisorJob(viewModelScope.coroutineContext[Job])

    fun onActive() {
        isActive.value = true
    }

    fun onInactive() {
        isActive.value = false
        scope.coroutineContext.cancelChildren()
    }

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context, start, block)

    protected fun launchWhenActive(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
        isActive.filter { it }.collect {
            scope.launch(block = block)
        }
    }

    protected var state: S
        get() = mutableStates.value
        set(value) {
            mutableStates.value = value
        }

    interface State
}