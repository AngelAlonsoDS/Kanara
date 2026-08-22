package com.angelalonso.kanara.core.models

import com.angelalonso.kanara.core.utils.Gender
import java.time.LocalDate

/**
 * PatientDTO es la clase directa que se usa para usar junto a la entidad paciente de la base de datos
 * @param idPatient id de la entidad
 * @param firsName Nombre del paciente
 * @param paternalSurname Apellido paterno del paciente
 * @param maternalSurname Apellido materno del paciente
 * @param gender Género del paciente
 * @param birthdate Fecha de nacimiento
 * @param curp CURP del paciente
 * @param phone Teléfono celular de contacto del paciente
 * @param email Correo electrónico del paciente
 * @param address Dirección de vivienda del paciente
 * @param createAt Fecha del registro del paciente
 * @param active Estado de activo o archivado
 * @param createBy id del usuario que registro al paciente
 */
data class PatientDTO(
    val idPatient: Int,
    val firsName: String,
    val paternalSurname: String,
    val maternalSurname: String,
    val gender: Gender,
    val birthdate: LocalDate,
    val curp: String?,
    val phone: String?,
    val email: String?,
    val address: String?,
    val createAt: LocalDate,
    val active: Boolean,
    val createBy: Int
)

/**
 * Clase de la visa del paciente para mostrar un resumen de su información
 * @param idPatient id del paciente
 * @param fullName Nombre completo del paciente
 * @param gender Genero del paciente
 * @param age Edad del paciente
 * @param createAt Fecha del registro del paciente
 * @param lastScheduledDate Fecha de la última cita
 */
data class PatientVistDTO(
    val idPatient: Int,
    val fullName: String,
    val gender: Gender,
    val age: Int,
    val createAt: LocalDate,
    val lastScheduledDate: LocalDate
)