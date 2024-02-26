package com.chscorp.cosmeticsstore.presentation.states

data class LoginState(
    val email: String = "",
    val onEmailChange: (String) -> Unit = {},
    val password: String = "",
    val onPasswordChange: (String) -> Unit = {}
)