package net.softwarevillage.moneydragon.presentation.ui.screens.feed

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
class FeedViewModel @Inject constructor(
    private val localDataUseCase: GetLocalDataUseCase,
) : BaseViewModel<FeedUiState, FeedUiEvent, FeedUiEffect>() {

    override fun setInitialState(): FeedUiState = FeedUiState()

    init {
        isTransactionHave()
    }

    override fun handleEvent(event: FeedUiEvent) {
        when (event) {
            FeedUiEvent.GetTransactions -> {
                getTransactions()
            }
        }
    }


    private fun isTransactionHave() {
        viewModelScope.launch {
            localDataUseCase.isTransactionHave().handleResult(
                onComplete = {
                    setState(getCurrentState().copy(isLoading = false, isTransactionHave = it))
                },
                onError = {
                    setState(getCurrentState().copy(isLoading = false))
                    setEffect(FeedUiEffect.ShowMessage(it.localizedMessage as String))
                },
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
                }
            )
        }
    }

    private fun getTransactions() {
        viewModelScope.launch {
            localDataUseCase.getTransactions().handleResult(
                onComplete = {
                    setState(getCurrentState().copy(isLoading = false, transactions = it))
                },
                onError = {
                    setState(getCurrentState().copy(isLoading = false))
                    setEffect(FeedUiEffect.ShowMessage(it.localizedMessage as String))
                },
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
                }
            )
        }
    }

}

data class FeedUiState(
    var isLoading: Boolean = true,
    var isTransactionHave: Boolean = false,
    var transactions: List<TransactionUiModel> = emptyList(),
) : State

interface FeedUiEvent : Event {

    object GetTransactions : FeedUiEvent

}

interface FeedUiEffect : Effect {
    data class ShowMessage(val message: String? = "") : FeedUiEffect
}