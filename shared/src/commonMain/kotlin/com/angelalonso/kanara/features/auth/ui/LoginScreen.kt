package com.angelalonso.kanara.features.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angelalonso.kanara.core.ui.AppButton
import com.angelalonso.kanara.core.ui.AppTextField
import com.angelalonso.kanara.features.auth.AuthService
import com.angelalonso.kanara.features.auth.LoginResult
import com.angelalonso.kanara.theme.Spacing
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(authService: AuthService) {
    var usuarioField by remember { mutableStateOf("") }
    var passwordField by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf<String?>(null) }
    var isLoginIn by remember { mutableStateOf(false) }
    var usuarioLogueado by remember { mutableStateOf<LoginResult.Success?>(null) }
    val scope = rememberCoroutineScope()

    if (usuarioLogueado != null) {
        Text("Bienvenido, ${usuarioLogueado!!.nombreCompleto}")
        return
    }

    Column(
        Modifier.fillMaxSize().padding(Spacing.Medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Acceso al sistema", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(Spacing.Medium))

        AppTextField(
            value = usuarioField,
            onValueChange = {usuarioField = it; loginError = null},
            enabled = !isLoginIn
        )

        Spacer(modifier = Modifier.height(Spacing.Medium))

        AppTextField(
            value = passwordField,
            onValueChange = {passwordField = it; loginError = null},
            enabled = !isLoginIn
        )

        Spacer(modifier = Modifier.height(Spacing.Medium))

        loginError?.let {
            Text("Login error: $it", color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(Spacing.Medium))
        }

        AppButton(
            onClick = {
                isLoginIn = true
                loginError = null
                scope.launch {
                    when (val result = authService.login(usuarioField, passwordField)) {
                        is LoginResult.Success -> usuarioLogueado = result
                        is LoginResult.CredencialesInvalidas -> loginError = "Usuario o contraseña incorrectos"
                    }
                    isLoginIn = false
                }
            }
        ) {
            Text(if (isLoginIn) "Verificando ..." else "Acceder")
        }
    }
}