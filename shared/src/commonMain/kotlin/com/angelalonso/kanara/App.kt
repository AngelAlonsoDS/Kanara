package com.angelalonso.kanara

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.angelalonso.kanara.core.error.ErrorScreen
import com.angelalonso.kanara.core.ui.AppSnackbarProvider
import com.angelalonso.kanara.core.ui.window.WindowSizeProvider
import com.angelalonso.kanara.core.utils.InitState
import com.angelalonso.kanara.features.auth.ui.LoginScreen
import com.angelalonso.kanara.theme.AppTheme

@Composable
fun App(appContainer: AppContainer) {
    AppTheme {
        WindowSizeProvider { windowSize ->

            AppSnackbarProvider {
                var initState by remember {
                    mutableStateOf<InitState>(InitState.Loading)
                }

                var retryTrigger by remember {
                    mutableStateOf(0)
                }

                LaunchedEffect(retryTrigger) {
                    initState = try {
                        appContainer.databaseInitializer.initialize()
                        InitState.Success
                    } catch (e: Exception) {
                        InitState.Error(
                            e.message ?: "Something went wrong"
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            MaterialTheme.colorScheme.background
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    when (val state = initState) {
                        is InitState.Loading ->
                            SplashScreen()

                        is InitState.Success ->
                            LoginScreen(
                                authService = appContainer.authService,
                                windowSize = windowSize
                            )

                        is InitState.Error ->
                            ErrorScreen(
                                message = state.message,
                                onRetry = {
                                    retryTrigger += 1
                                }
                            )
                    }
                }
            }
        }
    }
}