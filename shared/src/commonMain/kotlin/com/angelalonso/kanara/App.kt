package com.angelalonso.kanara

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.angelalonso.kanara.core.error.ErrorScreen
import com.angelalonso.kanara.splash.SplashScreen
import com.angelalonso.kanara.core.utils.InitState
import com.angelalonso.kanara.db.AppContainer

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
                is InitState.Success -> InitScreen()
                is InitState.Error -> ErrorScreen(state.message, { retryTrigger++})
            }
        }
    }
}

@Composable
fun InitScreen() {
    var countExample by remember { mutableStateOf(0) }
    ErrorScreen("Hola q tal $countExample", { countExample++ }, "Aumentar contador")
}