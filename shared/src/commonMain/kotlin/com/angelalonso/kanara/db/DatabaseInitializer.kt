package com.angelalonso.kanara.db

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseInitializer(private val driverFactory: DatabaseDriverFactory) {

    private var database: AppDatabase? = null

    suspend fun initialize(): AppDatabase = withContext(Dispatchers.IO) {
        val driver = driverFactory.createDriver()
        AppDatabase(driver).also { database = it }
    }

    fun getDatabase(): AppDatabase =
        database ?: error("DB no inicializada — llama a initialize() primero")
}