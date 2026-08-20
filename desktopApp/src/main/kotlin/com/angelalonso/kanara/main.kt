package com.angelalonso.kanara

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.angelalonso.kanara.db.AppContainer
import com.angelalonso.kanara.db.DatabaseDriverFactory

fun main() = application {
    val appContainer = remember { AppContainer(DatabaseDriverFactory()) }
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kanara",
    ) {
        App(appContainer)
    }
}