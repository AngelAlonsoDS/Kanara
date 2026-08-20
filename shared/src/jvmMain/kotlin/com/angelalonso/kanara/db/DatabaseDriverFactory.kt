package com.angelalonso.kanara.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import java.io.File

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val dbPath = getDatabasePath()
        val driver: SqlDriver = JdbcSqliteDriver(
            url = "jdbc:sqlite:$dbPath",
            schema = AppDatabase.Schema // maneja create() y migrate() automáticamente por ti
        )
        driver.execute(null, "PRAGMA foreign_keys=ON;", 0) // esto SÍ hay que repetirlo en cada conexión
        return driver
    }

    private fun getDatabasePath(): String {
        val userHome = System.getProperty("user.home")
        val appDir = File(userHome, ".kanara")
        if (!appDir.exists()) appDir.mkdirs()
        return File(appDir, "kanara.db").absolutePath
    }
}