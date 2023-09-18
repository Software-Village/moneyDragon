package net.softwarevillage.moneydragon.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import net.softwarevillage.moneydragon.common.Resource

abstract class BaseViewModel<STATE : State, EVENT : Event, EFFECT : Effect> : ViewModel() {

    private val initialState: STATE by lazy { setInitialState() }

    abstract fun setInitialState(): STATE
    abstract fun handleEvent(event: EVENT)

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    private val _effect: MutableSharedFlow<EFFECT> = MutableSharedFlow()
    val effect: SharedFlow<EFFECT> = _effect.asSharedFlow()

    private val _event: MutableSharedFlow<EVENT> = MutableSharedFlow()
    val event: SharedFlow<EVENT> = _event.asSharedFlow()

    init {
        subscribeEvent()
    }

    private fun subscribeEvent() {
        viewModelScope.launch {
            _event.collect { handleEvent(it) }
        }
    }

    fun setState(state: STATE) {
        viewModelScope.launch { _state.emit(state) }
    }

    fun setEvent(event: EVENT) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun setEffect(effect: EFFECT) {
        viewModelScope.launch { _effect.emit(effect) }
    }

    fun getCurrentState() = state.value

    inline fun <T> Flow<Resource<T>>.handleResult(
        crossinline onComplete: (T) -> Unit,
        crossinline onError: (Exception) -> Unit,
        crossinline onLoading: () -> Unit
    ) {
        onEach { response ->
            when (response) {
                is Resource.Error -> {
                    onError(response.throwable)
                }

                is Resource.Success -> {
                    response.result?.let { onComplete(it) }
                }

                is Resource.Loading -> {
                    onLoading()
                }
            }
        }.launchIn(viewModelScope)
    }
}

interface State
interface Effect
interface Event

