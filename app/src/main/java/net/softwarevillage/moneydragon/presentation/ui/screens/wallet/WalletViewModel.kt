package net.softwarevillage.moneydragon.presentation.ui.screens.wallet

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.softwarevillage.moneydragon.common.base.BaseViewModel
import net.softwarevillage.moneydragon.common.base.Effect
import net.softwarevillage.moneydragon.common.base.Event
import net.softwarevillage.moneydragon.common.base.State
import net.softwarevillage.moneydragon.data.dto.local.CardDTO
import net.softwarevillage.moneydragon.data.dto.local.TransactionDTO
import net.softwarevillage.moneydragon.domain.model.CardUiModel
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.domain.useCase.local.GetLocalDataUseCase
import net.softwarevillage.moneydragon.domain.useCase.local.LocalInsertUseCase
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val localInsertUseCase: LocalInsertUseCase,
    private val getLocalDataUseCase: GetLocalDataUseCase,
) : BaseViewModel<WalletUiState, WalletEvent, WalletEffect>() {


    init {
        isCardHave()
    }

    override fun setInitialState(): WalletUiState = WalletUiState()

    override fun handleEvent(event: WalletEvent) {
        when (event) {
            is WalletEvent.AddCard -> addCard(event.cardDTO)
            WalletEvent.CardDetails -> getCardDetails()
            WalletEvent.IsTransactionHave -> isTransactionHave()
            WalletEvent.Transactions -> getTransactions()
        }
    }

    fun insertTransaction(transactionDTO: TransactionDTO) {
        viewModelScope.launch {
            localInsertUseCase.insertTransaction(transactionDTO)
        }
    }

    private fun getCardDetails() {
        viewModelScope.launch {
            getLocalDataUseCase.getCardDetails().handleResult(
                onComplete = {

                    setState(
                        getCurrentState().copy(
                            isLoading = false,
                            cardUiModel = it
                        )
                    )
                },
                onError = {
                    setState(getCurrentState().copy(isLoading = false))
                    setEffect(WalletEffect.ShowMessage(it.localizedMessage as String))
                },
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
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
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
                },
                onError = {
                    setState(getCurrentState().copy(isLoading = false))
                    setEffect(WalletEffect.ShowMessage(it.localizedMessage as String))
                }
            )
        }
    }

    private fun getTransactions() {
        viewModelScope.launch {
            getLocalDataUseCase.getTransactions().handleResult(
                onComplete = {
                    setState(
                        getCurrentState().copy(
                            isLoading = false,
                            transactions = it
                        )
                    )
                },
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
                },
                onError = {
                    setState(getCurrentState().copy(isLoading = false))
                    setEffect(WalletEffect.ShowMessage(it.localizedMessage as String))
                }
            )
        }
    }

    private fun addCard(cardDTO: CardDTO) {
        viewModelScope.launch {
            localInsertUseCase.insertCard(cardDTO)
        }
    }

    private fun isCardHave() {
        viewModelScope.launch {
            getLocalDataUseCase.isCardRegistered().handleResult(
                onComplete = {
                    setState(getCurrentState().copy(isLoading = false, isCardHave = it))
                },
                onError = {
                    setEffect(WalletEffect.ShowMessage(it.localizedMessage as String))
                    setState(getCurrentState().copy(isLoading = false))
                },
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
                }
            )
        }
    }


}

data class WalletUiState(
    val isLoading: Boolean = false,
    val isCardHave: Boolean = false,
    val isTransactionHave: Boolean = false,
    val transactions: List<TransactionUiModel> = emptyList(),
    val cardUiModel: CardUiModel? = null,
) : State

sealed interface WalletEvent : Event {
    data class AddCard(val cardDTO: CardDTO) : WalletEvent

    object CardDetails : WalletEvent

    object IsTransactionHave : WalletEvent

    object Transactions : WalletEvent
}

sealed interface WalletEffect : Effect {
    data class ShowMessage(val message: String) : WalletEffect
}


