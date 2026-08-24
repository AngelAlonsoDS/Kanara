package com.angelalonso.kanara

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.angelalonso.kanara.core.error.ErrorScreen
import com.angelalonso.kanara.core.utils.InitState
import com.angelalonso.kanara.features.auth.ui.LoginScreen
import com.angelalonso.kanara.theme.AppTheme

@Composable
// @Preview
fun App(appContainer: AppContainer) {
    AppTheme {
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
                is InitState.Success -> LoginScreen(appContainer.authService)
                is InitState.Error -> ErrorScreen(state.message, { retryTrigger++})
            }
        }
    }
}
