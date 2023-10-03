package net.softwarevillage.moneydragon.presentation.ui.screens.transaction

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.softwarevillage.moneydragon.common.base.BaseViewModel
import net.softwarevillage.moneydragon.common.base.Effect
import net.softwarevillage.moneydragon.common.base.Event
import net.softwarevillage.moneydragon.common.base.State
import net.softwarevillage.moneydragon.data.dto.local.TransactionDTO
import net.softwarevillage.moneydragon.domain.model.CardUiModel
import net.softwarevillage.moneydragon.domain.useCase.local.GetLocalDataUseCase
import net.softwarevillage.moneydragon.domain.useCase.local.LocalInsertUseCase
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val insertUseCase: LocalInsertUseCase,
    private val getUseCase: GetLocalDataUseCase,
) : BaseViewModel<TransactionUiState, TransactionEvent, TransactionEffect>() {

    init {
        getData()
    }

    override fun setInitialState(): TransactionUiState = TransactionUiState()

    override fun handleEvent(event: TransactionEvent) {
        when (event) {
            is TransactionEvent.AddTransaction -> {
                addTransaction(event.transactionDTO)
            }

            is TransactionEvent.UpdateBalance -> {
                updateBalance(event.balance)
            }
        }
    }

    private fun addTransaction(transactionDTO: TransactionDTO) {
        viewModelScope.launch {
            insertUseCase.insertTransaction(transactionDTO)
        }
    }

    private fun updateBalance(balance: Double) {
        viewModelScope.launch {
            insertUseCase.updateBalance(balance)
        }
    }

    private fun getData() {
        viewModelScope.launch {
            getUseCase.getCardDetails().handleResult(
                onComplete = {
                    setState(getCurrentState().copy(isLoading = false, cardData = it))
                },
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
                },
                onError = {
                    setState(getCurrentState().copy(isLoading = false))
                    setEffect(TransactionEffect.ShowMessage(it.localizedMessage as String))
                }
            )
        }
    }


}

data class TransactionUiState(
    var isLoading: Boolean = false,
    var cardData: CardUiModel? = null,
) : State

sealed class TransactionEvent : Event {
    data class AddTransaction(val transactionDTO: TransactionDTO) : TransactionEvent()

    data class UpdateBalance(val balance: Double) : TransactionEvent()
}

sealed class TransactionEffect : Effect {
    data class ShowMessage(val message: String) : TransactionEffect()
}