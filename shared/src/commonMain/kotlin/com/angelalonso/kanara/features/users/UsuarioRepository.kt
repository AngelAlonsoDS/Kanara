package com.angelalonso.kanara.features.users

import com.angelalonso.kanara.db.DatabaseInitializer
import com.angelalonso.kanara.db.Usuarios
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsuarioRepository(private val databaseInitializer: DatabaseInitializer) {

    private val queries get() = databaseInitializer.getDatabase().appDatabaseQueries

    suspend fun findByUsuario(usuario: String): Usuarios? = withContext(Dispatchers.IO) {
        queries.selectByUsuario(usuario).executeAsOneOrNull()
    }

    suspend fun findById(id: Long): Usuarios? = withContext(Dispatchers.IO) {
        queries.selectById(id).executeAsOneOrNull()
    }

    suspend fun findActivos(): List<Usuarios> = withContext(Dispatchers.IO) {
        queries.selectActivos().executeAsList()
    }

    suspend fun crear(nombreCompleto: String, usuario: String, passwordHash: String, rol: String): Usuarios =
        withContext(Dispatchers.IO) {
            queries.insertUsuario(nombreCompleto, usuario, passwordHash, rol).executeAsOne()
        }

    suspend fun actualizarPasswordHash(id: Long, nuevoHash: String) = withContext(Dispatchers.IO) {
        queries.updatePasswordHash(nuevoHash, id)
    }

    suspend fun desactivar(id: Long) = withContext(Dispatchers.IO) {
        queries.desactivarUsuario(id)
    }
}