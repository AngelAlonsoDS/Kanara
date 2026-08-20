package com.angelalonso.kanara.db

class AppContainer(driverFactory: DatabaseDriverFactory) {
    val databaseInitializer = DatabaseInitializer(driverFactory)
}