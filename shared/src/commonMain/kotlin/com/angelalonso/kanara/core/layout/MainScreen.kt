package com.angelalonso.kanara.core.layout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angelalonso.kanara.AppContainer
import com.angelalonso.kanara.core.layout.components.AppDrawer
import com.angelalonso.kanara.core.layout.components.AppText
import com.angelalonso.kanara.core.utils.Route
import com.angelalonso.kanara.features.patients.ui.PacienteScreen

@Composable
fun MainScreen(
    appContainer: AppContainer
) {
    var currentRoute by remember {
        mutableStateOf<Route>(Route.PacienteList)
    }

    val navController = rememberNavController()

    AppShell(
        drawer = {
            AppDrawer(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    currentRoute = route

                    navController.navigate(route) {
                        launchSingleTop = true
                    }
                }
            )
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = Route.PacienteList,
                modifier = Modifier.fillMaxSize()
            ){

                composable<Route.PacienteList> {
                    PacienteScreen()
                }

                composable<Route.UsuarioList> {
                    AppText("UsuarioList")
                }
            }
        }
    )
}