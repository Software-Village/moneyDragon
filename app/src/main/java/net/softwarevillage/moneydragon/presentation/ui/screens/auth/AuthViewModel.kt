package net.softwarevillage.moneydragon.presentation.ui.screens.auth

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import net.softwarevillage.moneydragon.common.base.BaseViewModel
import net.softwarevillage.moneydragon.common.base.Effect
import net.softwarevillage.moneydragon.common.base.Event
import net.softwarevillage.moneydragon.common.base.State
import net.softwarevillage.moneydragon.data.service.local.DataStoreRepository
import net.softwarevillage.moneydragon.domain.useCase.remote.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val dataStoreRepository: DataStoreRepository,
) : BaseViewModel<AuthUiState, AuthEvent, AuthEffect>() {
    override fun setInitialState(): AuthUiState = AuthUiState()

    override fun handleEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.LoginUser -> {
                login(event.email, event.password)
            }

            is AuthEvent.RegisterUser -> {
                register(event.email, event.password)
            }

            is AuthEvent.OnboardComplete -> {
                saveOnboardingState()
            }
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            authUseCase.login(email, password).handleResult(onComplete = {
                setEffect(AuthEffect.ShowMessage(isLogin = true))
                setState(AuthUiState(false))
            }, onError = {
                setEffect(
                    AuthEffect.ShowMessage(
                        isLogin = false,
                        message = it.localizedMessage ?: "Error"
                    )
                )
                setState(AuthUiState(false))
            }, onLoading = {
                setState(AuthUiState(true))
            })
        }
    }

    private fun register(email: String, password: String) {
        viewModelScope.launch {
            authUseCase.register(email, password).handleResult(onComplete = {
                setEffect(AuthEffect.ShowMessage(isLogin = true))
                setState(AuthUiState(false))
            }, onError = {
                setEffect(
                    AuthEffect.ShowMessage(
                        isLogin = false,
                        message = it.localizedMessage ?: "Error"
                    )
                )
                setState(AuthUiState(false))
            }, onLoading = {
                setState(AuthUiState(true))
            })
        }
    }

    private fun saveOnboardingState() {
        viewModelScope.launch {
            dataStoreRepository.setOnboardState(true)
        }
    }

    fun getOnboardComplete() {
        viewModelScope.launch {
            dataStoreRepository.getOnboardState.collectLatest {
                setState(AuthUiState(isCompleted = it ?: false))
            }
        }
    }
}


data class AuthUiState(
    val isLoading: Boolean = false,
    val isCompleted: Boolean = false
) : State

sealed interface AuthEffect : Effect {
    data class ShowMessage(val message: String? = null, val isLogin: Boolean = false) : AuthEffect
}

sealed interface AuthEvent : Event {
    data class LoginUser(val email: String, val password: String) : AuthEvent
    data class RegisterUser(val email: String, val password: String) : AuthEvent
    object OnboardComplete : AuthEvent
}