package net.softwarevillage.moneydragon.presentation.ui.screens.details

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
class TransactionDetailsViewModel @Inject constructor(
    private val getLocalDataUseCase: GetLocalDataUseCase,
) : BaseViewModel<TransactionDetailsState, TransactionDetailsEvent, TransactionDetailsEffect>() {
    override fun setInitialState(): TransactionDetailsState = TransactionDetailsState()

    override fun handleEvent(event: TransactionDetailsEvent) {
        when (event) {
            is TransactionDetailsEvent.TransactionDetails -> {
                getTransactionDetails(event.id)
            }
        }
    }

    private fun getTransactionDetails(id: Int) {
        viewModelScope.launch {
            getLocalDataUseCase.getTransactionDetails(id).handleResult(
                onComplete = {
                    setState(getCurrentState().copy(isLoading = false, transactionUiModel = it))
                },
                onError = {
                    setState(getCurrentState().copy(isLoading = false))
                    setEffect(TransactionDetailsEffect.ShowMessage(it.localizedMessage as String))
                },
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
                }
            )
        }
    }


}

data class TransactionDetailsState(
    var isLoading: Boolean = false,
    var transactionUiModel: TransactionUiModel? = null,
) : State

sealed interface TransactionDetailsEvent : Event {
    data class TransactionDetails(val id: Int) : TransactionDetailsEvent
}

sealed interface TransactionDetailsEffect : Effect {
    data class ShowMessage(val message: String) : TransactionDetailsEffect
}