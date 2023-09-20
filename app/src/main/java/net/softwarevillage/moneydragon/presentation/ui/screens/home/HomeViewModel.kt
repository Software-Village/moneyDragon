package net.softwarevillage.moneydragon.presentation.ui.screens.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.softwarevillage.moneydragon.common.base.BaseViewModel
import net.softwarevillage.moneydragon.common.base.Effect
import net.softwarevillage.moneydragon.common.base.Event
import net.softwarevillage.moneydragon.common.base.State
import net.softwarevillage.moneydragon.domain.model.CardUiModel
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

    override fun setInitialState(): HomeUiState = HomeUiState.Loading

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
                    setState(HomeUiState.Loading)
                },
                onError = {
                    setEffect(HomeEffect.ShowMessage(it.localizedMessage as String))
                    setState(HomeUiState.Error)
                },
                onComplete = {
                    setState(HomeUiState.IsCardRegistered(it))
                }
            )
        }
    }

    private fun isTransactionHave() {
        viewModelScope.launch {
            getLocalDataUseCase.isTransactionHave().handleResult(
                onComplete = {
                    setState(HomeUiState.IsTransactionHave(it))
                },
                onError = {
                    setState(HomeUiState.Error)
                    setEffect(HomeEffect.ShowMessage(it.localizedMessage as String))
                },
                onLoading = {
                    setState(HomeUiState.Loading)
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

sealed interface HomeUiState : State {

    object Loading : HomeUiState

    object Error : HomeUiState

    data class IsCardRegistered(val isRegistered: Boolean) : HomeUiState


    data class IsTransactionHave(val isHave: Boolean) : HomeUiState

    data class CardData(val cardUiModel: CardUiModel) : HomeUiState

}