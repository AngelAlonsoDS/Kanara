package com.angelalonso.kanara.features.auth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.angelalonso.kanara.core.ui.AppButton
import com.angelalonso.kanara.core.ui.AppText
import com.angelalonso.kanara.core.ui.AppTextField
import com.angelalonso.kanara.core.ui.AppTextVariant
import com.angelalonso.kanara.core.ui.LocalSnackbarHostState
import com.angelalonso.kanara.core.ui.showAppSnackbar
import com.angelalonso.kanara.core.ui.window.WindowSize
import com.angelalonso.kanara.core.ui.window.formWidth
import com.angelalonso.kanara.core.utils.AppSnackbarVariant
import com.angelalonso.kanara.features.auth.AuthService
import com.angelalonso.kanara.features.auth.LoginResult
import com.angelalonso.kanara.theme.Spacing
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(authService: AuthService, windowSize: WindowSize, onLoginSuccess: () -> Unit) {
    val snackbarState = LocalSnackbarHostState.current

    var usuarioField by remember { mutableStateOf("") }
    var passwordField by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf<String?>(null) }
    var isLoginIn by remember { mutableStateOf(false) }
    var usuarioLogueado by remember { mutableStateOf<LoginResult.Success?>(null) }
    val scope = rememberCoroutineScope()

    if (usuarioLogueado != null) {
        snackbarState.showAppSnackbar(
            title = "Acceso al sistema",
            message = "Bienvenido, ${usuarioLogueado!!.nombreCompleto}",
            variant = AppSnackbarVariant.SUCCESS,
        )

        onLoginSuccess

        return
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .width(windowSize.formWidth(maxWidth))
                .widthIn(
                    min = 300.dp,
                    max = 600.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing.Large, Alignment.CenterVertically)
        ) {
            AppText("Acceso al sistema", variant = AppTextVariant.TITLE)

            AppText("Usuario", modifier = Modifier.fillMaxWidth() , textAlign = TextAlign.Left)

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = usuarioField,
                onValueChange = { usuarioField = it; loginError = null },
                enabled = !isLoginIn
            )

            AppText("Contraseña", modifier = Modifier.fillMaxWidth() , textAlign = TextAlign.Left)
            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordField,
                onValueChange = { passwordField = it; loginError = null },
                enabled = !isLoginIn
            )

            loginError?.let {
                snackbarState.showAppSnackbar(
                    title = "Acceso denegado",
                    message = "Login error: $it",
                    variant = AppSnackbarVariant.WARNING,
                )
            }

            Spacer(Modifier.height(Spacing.Small))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = if (isLoginIn) "Verificando ..." else "Acceder",
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
            )
        }
    }
}