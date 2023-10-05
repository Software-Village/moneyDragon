package net.softwarevillage.moneydragon.presentation.ui.screens.auth

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import net.softwarevillage.moneydragon.common.base.BaseViewModel
import net.softwarevillage.moneydragon.common.base.Effect
import net.softwarevillage.moneydragon.common.base.Event
import net.softwarevillage.moneydragon.common.base.State
import net.softwarevillage.moneydragon.data.dto.local.AuthDTO
import net.softwarevillage.moneydragon.data.service.local.DataStoreRepository
import net.softwarevillage.moneydragon.domain.useCase.local.LocalInsertUseCase
import net.softwarevillage.moneydragon.domain.useCase.remote.AuthUseCase
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val dataStoreRepository: DataStoreRepository,
    private val insertUseCase: LocalInsertUseCase,
) : BaseViewModel<AuthUiState, AuthEvent, AuthEffect>() {

    init {
        getOnboardComplete()
        getTokenState()
    }

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

            is AuthEvent.InsertAuth -> {
                insertAuth(event.authDTO)
            }

            is AuthEvent.TokenSaver -> {
                tokenSaver(event.token)
            }

            is AuthEvent.TokenState -> {
                getTokenState()
            }
        }
    }

    private fun tokenSaver(token: String) {
        viewModelScope.launch {
            dataStoreRepository.setTokenState(token)
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            authUseCase.login(email, password).handleResult(
                onComplete = {
                    setEffect(AuthEffect.ShowMessage(isLogin = true))
                    setState(getCurrentState().copy(isLoading = false, authResult = it))
                }, onError = {
                    setEffect(
                        AuthEffect.ShowMessage(
                            isLogin = false,
                            message = it.localizedMessage ?: "Error"
                        )
                    )
                    setState(getCurrentState().copy(isLoading = false))
            }, onLoading = {
                    setState(getCurrentState().copy(isLoading = true))
            })
        }
    }

    private fun register(email: String, password: String) {
        viewModelScope.launch {
            authUseCase.register(email, password).handleResult(onComplete = {
                setEffect(AuthEffect.ShowMessage(isLogin = true))
                setState(getCurrentState().copy(isLoading = false, authResult = it))
            }, onError = {
                setEffect(
                    AuthEffect.ShowMessage(
                        isLogin = false,
                        message = it.localizedMessage ?: "Error"
                    )
                )
                setState(getCurrentState().copy(isLoading = false))
            }, onLoading = {
                setState(getCurrentState().copy(isLoading = true))
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
                setState(getCurrentState().copy(isCompleted = it ?: false))
            }
        }
    }

    private fun insertAuth(authDTO: AuthDTO) {
        viewModelScope.launch {
            insertUseCase.insertAuth(authDTO)
        }
    }

    private fun getTokenState() {
        viewModelScope.launch {
            dataStoreRepository.getToken.collectLatest {
                setState(getCurrentState().copy(isTokenHave = !it.isNullOrEmpty()))
            }
        }
    }

}


data class AuthUiState(
    val isLoading: Boolean = false,
    val isTokenHave: Boolean = false,
    val isCompleted: Boolean = false,
    val authResult: AuthResult? = null,
) : State

sealed interface AuthEffect : Effect {
    data class ShowMessage(val message: String? = null, val isLogin: Boolean = false) : AuthEffect
}

sealed interface AuthEvent : Event {

    data class InsertAuth(val authDTO: AuthDTO) : AuthEvent
    data class LoginUser(val email: String, val password: String) : AuthEvent
    data class RegisterUser(val email: String, val password: String) : AuthEvent

    object TokenState : AuthEvent

    data class TokenSaver(val token: String) : AuthEvent

    object OnboardComplete : AuthEvent
}