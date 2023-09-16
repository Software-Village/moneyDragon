package net.softwarevillage.moneydragon.presentation.ui.screens.home


import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.softwarevillage.moneydragon.common.base.BaseViewModel
import net.softwarevillage.moneydragon.domain.model.CardUiModel
import net.softwarevillage.moneydragon.domain.model.TransactionUiModel
import net.softwarevillage.moneydragon.domain.useCase.local.LocalGetDataUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localGetDataUseCase: LocalGetDataUseCase,
) : BaseViewModel<HomeViewModel.HomeState>(HomeState.Loading) {

    init {
        getTransactions()
        getCardDetails()
    }

    private fun getTransactions() {
        viewModelScope.launch {
            localGetDataUseCase.getTransactions().handleResult(
                onComplete = {
                    setState(HomeState.Transactions(it))
                },
                onError = {
                    setState(HomeState.Error(it.localizedMessage as String))
                },
                onLoading = {
                    setState(HomeState.Loading)
                }
            )
        }
    }

    private fun getCardDetails() {
        viewModelScope.launch {
            localGetDataUseCase.getCardDetails().handleResult(
                onComplete = {
                    setState(HomeState.CardDetails(it))
                },
                onError = {
                    setState(HomeState.Error(it.localizedMessage as String))
                },
                onLoading = {
                    setState(HomeState.Loading)
                }
            )
        }
    }


    sealed class HomeState {

        object Loading : HomeState()

        data class Error(val message: String) : HomeState()

        data class CardDetails(val cardUiModel: CardUiModel) : HomeState()

        data class Transactions(val transactions: List<TransactionUiModel>) : HomeState()


    }


}