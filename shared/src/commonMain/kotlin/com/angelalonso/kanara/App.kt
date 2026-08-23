package com.angelalonso.kanara

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angelalonso.kanara.core.error.ErrorScreen
import com.angelalonso.kanara.core.utils.InitState
import com.angelalonso.kanara.features.auth.LoginResult
import kotlinx.coroutines.launch

@Composable
// @Preview
fun App(appContainer: AppContainer) {
    MaterialTheme {
        var initState by remember { mutableStateOf<InitState>(InitState.Loading) }
        var retryTrigger by remember { mutableStateOf(0) }

        // Carga la base de datos
        LaunchedEffect(retryTrigger) {
            initState = try {
                appContainer.databaseInitializer.initialize()
                InitState.Success
            } catch (e: Exception) {
                InitState.Error(e.message ?: "Something went wrong")
            }
        }

        Box(Modifier.fillMaxSize()) {
            when (val state = initState) {
                is InitState.Loading -> SplashScreen()
                is InitState.Success -> InitScreen(appContainer)
                is InitState.Error -> ErrorScreen(state.message, { retryTrigger++})
            }
        }
    }
}

@Composable
fun InitScreen(appContainer: AppContainer) {
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

    Column(Modifier.fillMaxSize().padding(28.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Login", style = MaterialTheme.typography.headlineSmall)
        TextField(
            value = usuarioField,
            onValueChange = {usuarioField = it; loginError = null},
            enabled = !isLoginIn
        )
        TextField(
            value = passwordField,
            onValueChange = {passwordField = it; loginError = null},
            enabled = !isLoginIn
        )

        loginError?.let { Text("Login error: $it", color = MaterialTheme.colorScheme.error) }

        Button(
            enabled = !isLoginIn && usuarioField.isNotBlank() && passwordField.isNotBlank(),
            onClick = {
                isLoginIn = true
                loginError = null
                scope.launch {
                    when (val result = appContainer.authService.login(usuarioField, passwordField)) {
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