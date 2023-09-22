package net.softwarevillage.moneydragon.presentation.ui.screens.chart

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.softwarevillage.moneydragon.common.base.BaseViewModel
import net.softwarevillage.moneydragon.common.base.Effect
import net.softwarevillage.moneydragon.common.base.Event
import net.softwarevillage.moneydragon.common.base.State
import net.softwarevillage.moneydragon.domain.model.CardUiModel
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.domain.useCase.local.GetLocalDataUseCase
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val getLocalDataUseCase: GetLocalDataUseCase,
) : BaseViewModel<ChartUiState, ChartEvent, ChartEffect>() {

    init {
        isCardHave()
        isTransactionHave()
    }

    override fun setInitialState(): ChartUiState = ChartUiState()

    override fun handleEvent(event: ChartEvent) {
        when (event) {
            ChartEvent.GetCardDetails -> getCardDetails()
            ChartEvent.GetTransactions -> getTransactions()
            ChartEvent.IsCardHave -> isCardHave()
            ChartEvent.IsTransactionHave -> isTransactionHave()
        }
    }

    private fun isCardHave() {
        viewModelScope.launch {
            getLocalDataUseCase.isCardRegistered().handleResult(
                onComplete = {
                    setState(getCurrentState().copy(isLoading = false, isCardHave = it))
                },
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
                },
                onError = {
                    setState(getCurrentState().copy(isLoading = false))
                    setEffect(ChartEffect.ShowMessage(it.localizedMessage as String))
                }
            )
        }
    }

    private fun isTransactionHave() {
        viewModelScope.launch {
            getLocalDataUseCase.isTransactionHave().handleResult(
                onComplete = {
                    setState(getCurrentState().copy(isLoading = false, isTransactionHave = it))
                },
                onError = {
                    setState(getCurrentState().copy(isLoading = false))
                    setEffect(ChartEffect.ShowMessage(it.localizedMessage as String))
                },
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
                }
            )
        }
    }

    private fun getCardDetails() {
        viewModelScope.launch {
            getLocalDataUseCase.getCardDetails().handleResult(
                onComplete = {
                    setState(getCurrentState().copy(isLoading = false, cardUiModel = it))
                },
                onError = {
                    setState(getCurrentState().copy(isLoading = false))
                    setEffect(ChartEffect.ShowMessage(it.localizedMessage as String))
                },
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
                }
            )
        }
    }

    private fun getTransactions() {
        viewModelScope.launch {
            getLocalDataUseCase.getTransactions().handleResult(
                onComplete = {
                    setState(getCurrentState().copy(isLoading = false, transactions = it))
                },
                onError = {
                    setState(getCurrentState().copy(isLoading = false))
                    setEffect(ChartEffect.ShowMessage(it.localizedMessage as String))
                },
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
                }
            )
        }
    }


}


data class ChartUiState(
    val isLoading: Boolean = false,
    val isCardHave: Boolean = false,
    val isTransactionHave: Boolean = false,
    val cardUiModel: CardUiModel? = null,
    val transactions: List<TransactionUiModel> = emptyList(),

    ) : State

sealed interface ChartEvent : Event {

    object IsCardHave : ChartEvent

    object IsTransactionHave : ChartEvent

    object GetCardDetails : ChartEvent

    object GetTransactions : ChartEvent

}

sealed interface ChartEffect : Effect {
    data class ShowMessage(val message: String? = null) : ChartEffect
}