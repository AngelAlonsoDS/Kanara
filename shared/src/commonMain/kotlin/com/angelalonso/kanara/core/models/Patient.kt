package com.angelalonso.kanara.core.models

import com.angelalonso.kanara.core.utils.Sex
import java.time.LocalDate

/**
 * PatientDTO es la clase directa que se usa para usar junto a la entidad paciente de la base de datos
 * @param idPatient es el id de la entidad
 * @param firsName es el nombre del paciente
 * @param paternalSurname es el apellido paterno del paciente
 * @param maternalSurname es el apellido materno del paciente
 * @param sex es el sexo del paciente
 */
data class PatientDTO(
    val idPatient: Int,
    val firsName: String,
    val paternalSurname: String,
    val maternalSurname: String,
    val sex: Sex,
    val birthdate: LocalDate,
    val curp: String?,
    val phone: String?,
    val email: String?,
    val address: String?,
    val createAt: LocalDate,
    val active: Boolean,
    val createBy: Int
)

data class PatientVistDTO(
    val idPatient: Int,
    val fullName: String,
    val sex: Sex,
    val age: Int
)