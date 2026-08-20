package com.angelalonso.kanara

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.angelalonso.kanara.db.DatabaseDriverFactory
import com.angelalonso.kanara.db.DatabaseInitializer
import com.angelalonso.kanara.error.ErrorScreen
import com.angelalonso.kanara.splash.SplashScreen
import com.angelalonso.kanara.utils.InitState
import com.angelalonso.kanara.db.AppContainer

import kanara.shared.generated.resources.Res
import kanara.shared.generated.resources.compose_multiplatform
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

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
                is InitState.Success -> Text(
                    "Hola",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
                is InitState.Error -> ErrorScreen(state.message, { retryTrigger++})
            }
        }
    }
}