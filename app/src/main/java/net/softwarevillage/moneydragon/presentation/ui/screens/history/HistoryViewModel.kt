package net.softwarevillage.moneydragon.presentation.ui.screens.history

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.softwarevillage.moneydragon.common.base.BaseViewModel
import net.softwarevillage.moneydragon.common.base.Effect
import net.softwarevillage.moneydragon.common.base.Event
import net.softwarevillage.moneydragon.common.base.State
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.domain.useCase.local.GetLocalDataUseCase
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getLocalDataUseCase: GetLocalDataUseCase,
) : BaseViewModel<HistoryState, HistoryEvent, HistoryEffect>() {

    init {
        getTransactions()
    }

    override fun setInitialState(): HistoryState = HistoryState()


    override fun handleEvent(event: HistoryEvent) {
        when (event) {
            HistoryEvent.GetTransactions -> {
                getTransactions()
            }
        }
    }

    private fun getTransactions() {
        viewModelScope.launch {
            getLocalDataUseCase.getTransactions().handleResult(
                onComplete = {
                    setState(getCurrentState().copy(isLoading = false, transactionData = it))
                },
                onError = {
                    setState(getCurrentState().copy(isLoading = false))
                    setEffect(HistoryEffect.ShowMessage(it.localizedMessage as String))
                },
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
                }
            )
        }
    }


}

data class HistoryState(
    var isLoading: Boolean = false,
    var transactionData: List<TransactionUiModel> = emptyList(),
) : State

sealed class HistoryEvent : Event {

    object GetTransactions : HistoryEvent()

}

sealed class HistoryEffect : Effect {

    data class ShowMessage(val message: String) : HistoryEffect()

}

