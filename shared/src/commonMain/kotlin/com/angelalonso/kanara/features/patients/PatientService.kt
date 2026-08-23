package com.angelalonso.kanara.features.patients

import com.angelalonso.kanara.db.AppDatabase

class PatientService {
    var database: AppDatabase

    constructor(database: AppDatabase){
        this.database = database
    }

    public fun patientList(): Int {
        return 7
    }
}