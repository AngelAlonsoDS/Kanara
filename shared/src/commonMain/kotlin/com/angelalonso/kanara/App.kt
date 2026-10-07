package com.angelalonso.kanara

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angelalonso.kanara.core.error.ErrorScreen
import com.angelalonso.kanara.core.layout.MainScreen
import com.angelalonso.kanara.core.layout.components.AppSnackbarProvider
import com.angelalonso.kanara.core.ui.LocalUserSession
import com.angelalonso.kanara.core.ui.window.WindowSize
import com.angelalonso.kanara.core.ui.window.WindowSizeProvider
import com.angelalonso.kanara.core.utils.InitState
import com.angelalonso.kanara.core.utils.Route
import com.angelalonso.kanara.features.auth.UserSession
import com.angelalonso.kanara.features.auth.ui.LoginScreen
import com.angelalonso.kanara.features.patients.ui.PacienteScreen
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

                var userSession by remember {
                    mutableStateOf<UserSession?>(null)
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
                        is InitState.Loading -> SplashScreen()
                        is InitState.Error ->
                            ErrorScreen(
                                message = state.message,
                                onRetry = {
                                    retryTrigger += 1
                                }
                            )
                        is InitState.Success ->
                            CompositionLocalProvider(LocalUserSession provides userSession) {
                                navigation(
                                    appContainer,
                                    windowSize,
                                    onLogin = { session ->
                                        userSession = session
                                    },
                                    onLogout = {
                                        userSession = null
                                    }
                                )
                            }

                    }
                }
            }
        }
    }
}

@Composable
fun navigation(
    appContainer: AppContainer,
    windowSize: WindowSize,
    onLogin: (UserSession) -> Unit,
    onLogout: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Login,
    ) {
        composable<Route.Login> {
            LoginScreen(
                authService = appContainer.authService,
                windowSize = windowSize,
                onLoginSuccess = { result ->
                    onLogin(
                        UserSession(
                            userId = result.userId,
                            nombreCompleto = result.nombreCompleto,
                            rol = result.rol
                        )
                    )

                    navController.navigate(Route.Home) {
                        popUpTo(Route.Login) { inclusive = true }
                    }
                }
            )
        }
        composable<Route.Home> {
            MainScreen(appContainer)
        }
    }
}