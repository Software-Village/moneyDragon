package net.softwarevillage.moneydragon.presentation.ui.screens.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.softwarevillage.moneydragon.common.base.BaseViewModel
import net.softwarevillage.moneydragon.common.base.Effect
import net.softwarevillage.moneydragon.common.base.Event
import net.softwarevillage.moneydragon.common.base.State
import net.softwarevillage.moneydragon.domain.useCase.local.GetLocalDataUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLocalDataUseCase: GetLocalDataUseCase,
) : BaseViewModel<HomeUiState, HomeEvent, HomeEffect>() {


    init {
        isCardRegistered()
        isTransactionHave()
    }

    override fun setInitialState(): HomeUiState = HomeUiState()

    override fun handleEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.IsCardRegistered -> isCardRegistered()
            HomeEvent.IsTransactionHave -> isTransactionHave()
        }
    }


    private fun isCardRegistered() {
        viewModelScope.launch {
            getLocalDataUseCase.isCardRegistered().handleResult(
                onLoading = {
                    setState(HomeUiState())
                },
                onError = {
                    setState(HomeUiState(isLoading = false))
                    setEffect(HomeEffect.ShowMessage(it.localizedMessage as String))
                },
                onComplete = {
                    setState(getCurrentState().copy(isLoading = false, isRegistered = it))
                }
            )
        }
    }

    private fun isTransactionHave() {
        viewModelScope.launch {
            getLocalDataUseCase.isTransactionHave().handleResult(
                onComplete = {
                    setState(getCurrentState().copy(isLoading = false, isHave = it))
                },
                onError = {
                    setState(HomeUiState(isLoading = false))
                    setEffect(HomeEffect.ShowMessage(it.localizedMessage as String))
                },
                onLoading = {
                    setState(HomeUiState())
                }
            )
        }
    }

}

sealed interface HomeEffect : Effect {

    data class ShowMessage(val message: String? = null) : HomeEffect

}

sealed interface HomeEvent : Event {

    object IsCardRegistered : HomeEvent

    object IsTransactionHave : HomeEvent

}

data class HomeUiState(
    val isLoading: Boolean = true,
    val isRegistered: Boolean = false,
    val isHave: Boolean = false
) : State






