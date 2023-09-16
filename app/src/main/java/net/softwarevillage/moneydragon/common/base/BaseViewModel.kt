package net.softwarevillage.moneydragon.common.base

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import net.softwarevillage.moneydragon.common.Resource

abstract class BaseViewModel<UiState>(initialState: UiState) : ViewModel() {

    private val _state = mutableStateOf(initialState)
    private val state: State<UiState> get() = _state

    fun setState(state: UiState) {
        viewModelScope.launch {
            _state.value = state
        }
    }


    fun getCurrentState() = state.value

    inline fun <T> Flow<Resource<T>>.handleResult(
        crossinline onComplete: (T) -> Unit,
        crossinline onError: (Throwable) -> Unit,
        crossinline onLoading: () -> Unit,
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

