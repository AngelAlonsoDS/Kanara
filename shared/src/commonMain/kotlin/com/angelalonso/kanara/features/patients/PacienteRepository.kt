package com.angelalonso.kanara.features.patients

import com.angelalonso.kanara.db.DatabaseInitializer
import com.angelalonso.kanara.db.Pacientes
import com.angelalonso.kanara.db.Vista_pacientes_resumen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PacienteRepository(private val databaseInitializer: DatabaseInitializer) {

    private val queries get() = databaseInitializer.getDatabase().appDatabaseQueries

    suspend fun findById(id: Long): Pacientes? = withContext(Dispatchers.IO) {
        queries.selectPacienteById(id).executeAsOneOrNull()
    }

    suspend fun findByCurp(curp: String): Pacientes? = withContext(Dispatchers.IO) {
        queries.selectPacienteByCurp(curp).executeAsOneOrNull()
    }

    suspend fun findActivos(): List<Pacientes> = withContext(Dispatchers.IO) {
        queries.selectPacientesActivos().executeAsList()
    }

    suspend fun findArchivados(): List<Pacientes> = withContext(Dispatchers.IO) {
        queries.selectPacientesArchivados().executeAsList()
    }

    suspend fun buscar(texto: String): List<Vista_pacientes_resumen> = withContext(Dispatchers.IO) {
        queries.buscarPacientesActivos(texto).executeAsList()
    }

    suspend fun crear(
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String?,
        sexo: String,
        fechaNacimiento: String,
        lugarNacimiento: String?,
        curp: String?,
        escolaridad: String?,
        ocupacion: String?,
        estadoCivil: String?,
        religion: String?,
        telefono: String?,
        email: String?,
        direccion: String?,
        creadoPor: Long?
    ): Pacientes = withContext(Dispatchers.IO) {
        queries.insertPaciente(
            nombre, apellidoPaterno, apellidoMaterno, sexo, fechaNacimiento,
            lugarNacimiento, curp, escolaridad, ocupacion, estadoCivil, religion,
            telefono, email, direccion, creadoPor
        ).executeAsOne()
    }

    suspend fun actualizar(
        id: Long,
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String?,
        sexo: String,
        fechaNacimiento: String,
        lugarNacimiento: String?,
        curp: String?,
        escolaridad: String?,
        ocupacion: String?,
        estadoCivil: String?,
        religion: String?,
        telefono: String?,
        email: String?,
        direccion: String?
    ) = withContext(Dispatchers.IO) {
        queries.updatePaciente(
            nombre, apellidoPaterno, apellidoMaterno, sexo, fechaNacimiento,
            lugarNacimiento, curp, escolaridad, ocupacion, estadoCivil, religion,
            telefono, email, direccion, id
        )
    }

    suspend fun archivar(id: Long) = withContext(Dispatchers.IO) {
        queries.archivarPaciente(id)
    }

    suspend fun reactivar(id: Long) = withContext(Dispatchers.IO) {
        queries.reactivarPaciente(id)
    }
}