package com.chscorp.cosmeticsstore.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chscorp.cosmeticsstore.presentation.states.LoginState
import com.chscorp.cosmeticsstore.presentation.ui.theme.CosmeticsStoreTheme
import com.chscorp.cosmeticsstore.presentation.ui.theme.DeepPeachSoft
import com.chscorp.cosmeticsstore.presentation.ui.viewModel.LoginViewModel


@Composable
fun LoginScreenStateful(viewModel: LoginViewModel) {
    val state by viewModel.loginUiState.collectAsState()
    LoginScreenStateless(state = state)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenStateless(state: LoginState) {
    Column (
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.width(30.dp))
        TextField(
            value = state.email,
            onValueChange = state.onEmailChange,
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )

        var isPasswordVisible by remember { mutableStateOf(false) }

        val trailingIcon = @Composable {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    if (isPasswordVisible) {
                        Icons.Default.VisibilityOff
                    } else Icons.Default.Visibility,
                    contentDescription = "",
                    tint = DeepPeachSoft
                )
            }
        }

        TextField(
            value = state.password,
            onValueChange = state.onPasswordChange,
            Modifier
                .fillMaxWidth(),
            trailingIcon = trailingIcon,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(
                onDone = {  }
            ),
            placeholder = { Text("Senha") },
            label = { Text("Senha") },
            singleLine = true,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        Button(
            colors = ButtonDefaults.buttonColors(DeepPeachSoft),
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login", color = Color.White)
        }
        Button(
            colors = ButtonDefaults.buttonColors(DeepPeachSoft),
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Cadastrar", color = Color.White)
        }
        Spacer(modifier = Modifier)
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    CosmeticsStoreTheme {
        Surface {
            //LoginScreenStateless()
        }
    }
}