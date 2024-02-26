package com.chscorp.cosmeticsstore.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import com.chscorp.cosmeticsstore.presentation.states.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _loginUiState: MutableStateFlow<LoginState> = MutableStateFlow(
        LoginState()
    )
    val loginUiState get() = _loginUiState.asStateFlow()

    init {
        _loginUiState.update { currentState ->
            currentState.copy(
                onEmailChange = {
                    _loginUiState.value = _loginUiState.value.copy(
                        email = it
                    )
                },
                onPasswordChange = {
                    _loginUiState.value = _loginUiState.value.copy(
                        password = it
                    )
                }
            )
        }
    }
}