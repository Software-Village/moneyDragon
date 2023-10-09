package net.softwarevillage.moneydragon.presentation.ui.screens.profile

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.softwarevillage.moneydragon.common.base.BaseViewModel
import net.softwarevillage.moneydragon.common.base.Effect
import net.softwarevillage.moneydragon.common.base.Event
import net.softwarevillage.moneydragon.common.base.State
import net.softwarevillage.moneydragon.domain.model.AuthUiModel
import net.softwarevillage.moneydragon.domain.useCase.local.GetLocalDataUseCase
import net.softwarevillage.moneydragon.domain.useCase.local.LocalInsertUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val insertUseCase: LocalInsertUseCase,
    private val getUseCase: GetLocalDataUseCase,
) : BaseViewModel<ProfileUiState, ProfileEvent, ProfileEffect>() {

    override fun setInitialState(): ProfileUiState = ProfileUiState()

    init {
        getProfileData()
    }

    override fun handleEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.UpdatePhoto -> {
                updatePhoto(event.image)
            }
        }
    }

    private fun updatePhoto(image: ByteArray?) {
        viewModelScope.launch {
            insertUseCase.updateUserPhoto(image)
        }
    }

    private fun getProfileData() {
        viewModelScope.launch {
            getUseCase.getAuthDetails().handleResult(
                onComplete = {
                    setState(
                        getCurrentState().copy(
                            isLoading = false, authModel = it
                        )
                    )
                },
                onError = {
                    setState(getCurrentState().copy(isLoading = false))
                    setEffect(ProfileEffect.ShowMessage(it.localizedMessage as String))
                },
                onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
                }
            )
        }
    }


}

data class ProfileUiState(
    var isLoading: Boolean = false,
    var authModel: AuthUiModel? = null,
) : State

sealed interface ProfileEvent : Event {
    data class UpdatePhoto(val image: ByteArray?) : ProfileEvent
}

sealed interface ProfileEffect : Effect {

    data class ShowMessage(val message: String) : ProfileEffect

}