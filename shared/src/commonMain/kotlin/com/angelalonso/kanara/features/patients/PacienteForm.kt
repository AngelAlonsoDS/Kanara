package com.angelalonso.kanara.features.patients

data class PacienteForm(
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String? = null,
    val sexo: String,
    val fechaNacimiento: String,
    val lugarNacimiento: String? = null,
    val curp: String? = null,
    val escolaridad: String? = null,
    val ocupacion: String? = null,
    val estadoCivil: String? = null,
    val religion: String? = null,
    val telefono: String? = null,
    val email: String? = null,
    val direccion: String? = null
)

